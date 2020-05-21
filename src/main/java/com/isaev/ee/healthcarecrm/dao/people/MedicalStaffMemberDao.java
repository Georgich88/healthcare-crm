package com.isaev.ee.healthcarecrm.dao.people;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.isaev.ee.healthcarecrm.dao.Dao;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffMember;

@Component
public class MedicalStaffMemberDao implements Dao<MedicalStaffMember> {

	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Optional<MedicalStaffMember> findById(UUID id) {
		var entityManager = entityManagerFactory.createEntityManager();
		return Optional.ofNullable(entityManager.find(MedicalStaffMember.class, id));
	}

	@Override
	public List<MedicalStaffMember> findAll() {
		var entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("SELECT b FROM MedicalStaffMember b");
		var resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	@Override
	public void save(MedicalStaffMember member) {
		executeInsideTransaction(entityManager -> {
			entityManager.persist(member);});	
	}

	@Override
	public void update(MedicalStaffMember member) {
		executeInsideTransaction(entityManager -> {
			entityManager.merge(member);});		
	}

	@Override
	public void delete(MedicalStaffMember member) {
		executeInsideTransaction(entityManager -> entityManager.remove(member));		
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
