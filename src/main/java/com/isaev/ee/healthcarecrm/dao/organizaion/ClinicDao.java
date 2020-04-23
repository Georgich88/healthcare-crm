package com.isaev.ee.healthcarecrm.dao.organizaion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.isaev.ee.healthcarecrm.dao.Dao;
import com.isaev.ee.healthcarecrm.domain.organization.Clinic;

public class ClinicDao implements Dao<Clinic> {

	@Autowired
	private EntityManager entityManager;
	
	private DepartmentDao departmentDao = new DepartmentDao();
	
	@Override
	public Optional<Clinic> get(UUID id) {
		return Optional.ofNullable(entityManager.find(Clinic.class, id));
	}

	@Override
	public List<Clinic> getAll() {
		Query query = entityManager.createQuery("SELECT b FROM Clinic b");
		return query.getResultList();
	}

	@Override
	public void save(Clinic clinic) {
		executeInsideTransaction(entityManager -> entityManager.persist(clinic));
		clinic.getDepartments().forEach(department -> departmentDao.save(department));
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
