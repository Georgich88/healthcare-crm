package com.isaev.ee.healthcarecrm.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Dao<T> {

	Optional<T> get(UUID id);
	
	List<T> getAll();
	
	void save(T t);
	
	void update(T t);
	
	void delete(T t);
	
	
}
