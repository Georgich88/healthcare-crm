package com.isaev.ee.healthcarecrm.selectionfactories;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

public interface SelectionFactory<T> {
	
	List<T> select(SelectionCriteria criteria);

}
