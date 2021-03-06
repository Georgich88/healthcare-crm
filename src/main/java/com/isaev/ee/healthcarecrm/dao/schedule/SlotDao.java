package com.isaev.ee.healthcarecrm.dao.schedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.isaev.ee.healthcarecrm.dao.Dao;
import com.isaev.ee.healthcarecrm.domain.schedule.Slot;

@Component
public class SlotDao implements Dao<Slot> {

	@PersistenceContext
	private EntityManager entityManager;

	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Optional<Slot> findById(UUID id) {
		var entityManager = entityManagerFactory.createEntityManager();
		return Optional.ofNullable(entityManager.find(Slot.class, id));
	}

	@Override
	public List<Slot> findAll(Pageable pageable) {
		var entityManager = entityManagerFactory.createEntityManager();
		List<Slot> resultList = findAllPageableResult(pageable, entityManager, Slot.class);	    
		entityManager.close();
		return resultList;
	}

	@Override
	public void save(Slot slot) {
		executeInsideTransaction(entityManager -> entityManager.persist(slot));
	}
	
	public void saveAll(List<Slot> slots) {
		executeInsideTransaction(entityManager -> slots.forEach(entityManager::persist));
	}

	@Override
	public void update(Slot slot) {		
		executeInsideTransaction(entityManager -> entityManager.merge(slot));	
	}

	@Override
	public void delete(Slot slot) {
		executeInsideTransaction(entityManager -> entityManager.remove(slot));		
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
