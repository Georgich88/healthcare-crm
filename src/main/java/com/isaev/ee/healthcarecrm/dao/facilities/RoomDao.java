package com.isaev.ee.healthcarecrm.dao.facilities;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaev.ee.healthcarecrm.dao.Dao;
import com.isaev.ee.healthcarecrm.domain.facilities.Room;

@Component
public class RoomDao implements Dao<Room> {

	@PersistenceContext
	private EntityManager entityManager;

	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Optional<Room> findById(UUID id) {
		var entityManager = entityManagerFactory.createEntityManager();
		return Optional.ofNullable(entityManager.find(Room.class, id));
	}

	@Override
	public List<Room> findAll() {
		var entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("SELECT b FROM Room b");
		return query.getResultList();
	}

	@Override
	public void save(Room room) {
		executeInsideTransaction(entityManager -> entityManager.persist(room));
	}

	@Override
	public void update(Room room) {		
		executeInsideTransaction(entityManager -> entityManager.merge(room));	
	}

	@Override
	public void delete(Room room) {
		executeInsideTransaction(entityManager -> entityManager.remove(room));		
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
