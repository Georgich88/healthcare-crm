package com.isaev.ee.healthcarecrm.dao.facilities;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.isaev.ee.healthcarecrm.dao.Dao;
import com.isaev.ee.healthcarecrm.domain.facilities.Building;

@Component
public class BuildingDao implements Dao<Building> {

	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private RoomDao roomDao;
	
	@Override
	public Optional<Building> findById(UUID id) {
		var entityManager = entityManagerFactory.createEntityManager();
		return Optional.ofNullable(entityManager.find(Building.class, id));
	}

	@Override
	public List<Building> findAll(Pageable pageable) {
		var entityManager = entityManagerFactory.createEntityManager();
		List<Building> resultList = findAllPageableResult(pageable, entityManager, Building.class);	    
		entityManager.close();
		return resultList;
	}

	@Override
	public void save(Building building) {
		executeInsideTransaction(entityManager -> {
			building.getRooms().forEach(room -> roomDao.save(room));
			entityManager.persist(building);});	
	}

	@Override
	public void update(Building building) {
		executeInsideTransaction(entityManager -> {
			building.getRooms().forEach(room -> roomDao.update(room));
			entityManager.merge(building);});		
	}

	@Override
	public void delete(Building building) {
		executeInsideTransaction(entityManager -> entityManager.remove(building));		
	}
	
	@Transactional
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
