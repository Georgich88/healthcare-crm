package com.isaev.ee.healthcarecrm.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;

public interface Dao<T> {

	Optional<T> findById(UUID id);

	List<T> findAll(Pageable pageable);

	void save(T t);

	void update(T t);

	void delete(T t);

	/**
	 * Find all elements using the pagination information.
	 * 
	 * @param pageable      - the pagination information
	 * @param entityManager - the persistence context
	 * @param typedClass    - the entity class to select
	 * @return the list of found elements on the page
	 */
	default List<T> findAllPageableResult(Pageable pageable, EntityManager entityManager, Class<T> typedClass) {

		int pageNumber = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(typedClass);
		Root<T> from = criteriaQuery.from(typedClass);
		CriteriaQuery<T> select = criteriaQuery.select(from);

		TypedQuery<T> typedQuery = entityManager.createQuery(select);
		typedQuery.setFirstResult(pageNumber - 1);
		typedQuery.setMaxResults(pageSize);

		List<T> resultList = typedQuery.getResultList();

		return resultList;
	}

}
