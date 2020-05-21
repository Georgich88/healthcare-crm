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
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	public List<Slot> findAll() {
		var entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("SELECT b FROM Slot b");
		var resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	@Override
	public void save(Slot slot) {
		executeInsideTransaction(entityManager -> entityManager.persist(slot));
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
