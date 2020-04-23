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
import com.isaev.ee.healthcarecrm.domain.organization.Department;


public class DepartmentDao implements Dao<Department> {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Optional<Department> get(UUID id) {
		return Optional.ofNullable(entityManager.find(Department.class, id));
	}

	@Override
	public List<Department> getAll() {
		Query query = entityManager.createQuery("SELECT b FROM Department b");
		return query.getResultList();
	}

	@Override
	public void save(Department room) {
		executeInsideTransaction(entityManager -> entityManager.persist(room));
	}

	@Override
	public void update(Department room) {
		executeInsideTransaction(entityManager -> entityManager.merge(room));
		
	}

	@Override
	public void delete(Department room) {
		executeInsideTransaction(entityManager -> entityManager.remove(room));		
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
