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
import com.isaev.ee.healthcarecrm.domain.people.Patient;

@Component
public class PatientDao implements Dao<Patient> {

	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Optional<Patient> findById(UUID id) {
		var entityManager = entityManagerFactory.createEntityManager();
		return Optional.ofNullable(entityManager.find(Patient.class, id));
	}

	@Override
	public List<Patient> findAll(Pageable pageable) {
		var entityManager = entityManagerFactory.createEntityManager();
		List<Patient> resultList = findAllPageableResult(pageable, entityManager, Patient.class);	    
		entityManager.close();
		return resultList;
	}

	@Override
	public void save(Patient patient) {
		executeInsideTransaction(entityManager -> {
			entityManager.persist(patient);});	
	}
	
	public void saveAll(List<Patient> patients) {
		executeInsideTransaction(entityManager -> patients.forEach(patient -> entityManager.persist(patient)));	
	}

	@Override
	public void update(Patient patient) {
		executeInsideTransaction(entityManager -> {
			entityManager.merge(patient);});		
	}

	@Override
	public void delete(Patient patient) {
		executeInsideTransaction(entityManager -> entityManager.remove(patient));		
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
