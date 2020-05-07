package com.isaev.ee.healthcarecrm.dao.schedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Timetable> findAll() {
		var entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("SELECT b FROM Timetable b");
		return query.getResultList();
	}

	@Override
	public void save(Timetable timetable) {
		executeInsideTransaction(entityManager -> {
			timetable.getSlots().forEach(room -> slotDao.save(room));
			entityManager.persist(timetable);});	
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
        }
        catch (RuntimeException e) {
        	transaction.rollback();
            throw e;
        }
    }

}
