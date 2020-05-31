package com.isaev.ee.healthcarecrm.dao.schedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.isaev.ee.healthcarecrm.dao.Dao;
import com.isaev.ee.healthcarecrm.domain.schedule.Timetable;

@Component
public class TimetableDao implements Dao<Timetable> {

	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private SlotDao slotDao;
	
	@Override
	public Optional<Timetable> findById(UUID id) {
		var entityManager = entityManagerFactory.createEntityManager();
		return Optional.ofNullable(entityManager.find(Timetable.class, id));
	}

	@Override
	public List<Timetable> findAll(Pageable pageable) {
		var entityManager = entityManagerFactory.createEntityManager();
		List<Timetable> resultList = findAllPageableResult(pageable, entityManager, Timetable.class);	    
		entityManager.close();
		return resultList;
	}

	@Override
	public void save(Timetable timetable) {
		executeInsideTransaction(entityManager -> {
			timetable.getSlots().forEach(room -> slotDao.save(room));
			entityManager.persist(timetable);});	
	}
	
	@Override
	public void saveAll(List<Timetable> timetables) {
		var slots = timetables.stream()
				.map(Timetable::getSlots)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		executeInsideTransaction(entityManager -> slots.forEach(entityManager::persist));			
		executeInsideTransaction(entityManager -> timetables.forEach(entityManager::persist));	
	}

	@Override
	public void update(Timetable timetable) {
		executeInsideTransaction(entityManager -> {
			timetable.getSlots().forEach(room -> slotDao.update(room));
			entityManager.merge(timetable);});		
	}

	@Override
	public void delete(Timetable timetable) {
		executeInsideTransaction(entityManager -> entityManager.remove(timetable));		
	}
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {

		var entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
        	transaction.begin();
            action.accept(entityManager);
            transaction.commit(); 
            entityManager.close();
        }
        catch (RuntimeException e) {
        	transaction.rollback();
        	entityManager.close();
            throw e;
        }
    }

}
