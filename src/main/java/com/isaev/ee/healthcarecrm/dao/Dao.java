package com.isaev.ee.healthcarecrm.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Dao<T> {

	Optional<T> findById(UUID id);
	
	List<T> findAll();
	
	void save(T t);
	
	void update(T t);
	
	void delete(T t);
	
	
}
