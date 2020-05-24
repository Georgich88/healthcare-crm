package com.isaev.ee.healthcarecrm.dao.organizaion;

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
import com.isaev.ee.healthcarecrm.domain.organization.Clinic;

@Component
public class ClinicDao implements Dao<Clinic> {

	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Optional<Clinic> findById(UUID id) {
		var entityManager = entityManagerFactory.createEntityManager();
		return Optional.ofNullable(entityManager.find(Clinic.class, id));
	}

	@Override
	public List<Clinic> findAll(Pageable pageable) {
		var entityManager = entityManagerFactory.createEntityManager();
		List<Clinic> resultList = findAllPageableResult(pageable, entityManager, Clinic.class);	    
		entityManager.close();
		return resultList;
	}

	@Override
	public void save(Clinic clinic) {
		executeInsideTransaction(entityManager -> entityManager.persist(clinic));
	}

	@Override
	public void update(Clinic clinic) {
		executeInsideTransaction(entityManager -> entityManager.merge(clinic));
		
	}

	@Override
	public void delete(Clinic clinic) {
		executeInsideTransaction(entityManager -> entityManager.remove(clinic));		
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
        	entityManager.close();
            throw e;
        }
    }

}
