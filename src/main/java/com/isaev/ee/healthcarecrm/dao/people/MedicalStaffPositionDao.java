package com.isaev.ee.healthcarecrm.dao.people;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.isaev.ee.healthcarecrm.dao.Dao;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffPosition;

@Component
public class MedicalStaffPositionDao implements Dao<MedicalStaffPosition> {

	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Optional<MedicalStaffPosition> findById(UUID id) {
		var entityManager = entityManagerFactory.createEntityManager();
		return Optional.ofNullable(entityManager.find(MedicalStaffPosition.class, id));
	}

	@Override
	public List<MedicalStaffPosition> findAll(Pageable pageable) {
		var entityManager = entityManagerFactory.createEntityManager();
		List<MedicalStaffPosition> resultList = findAllPageableResult(pageable, entityManager, MedicalStaffPosition.class);	    
		entityManager.close();
		return resultList;
	}

	@Override
	public void save(MedicalStaffPosition position) {
		executeInsideTransaction(entityManager -> {
			entityManager.persist(position);});	
	}

	@Override
	public void update(MedicalStaffPosition position) {
		executeInsideTransaction(entityManager -> {
			entityManager.merge(position);});		
	}

	@Override
	public void delete(MedicalStaffPosition position) {
		executeInsideTransaction(entityManager -> entityManager.remove(position));		
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
