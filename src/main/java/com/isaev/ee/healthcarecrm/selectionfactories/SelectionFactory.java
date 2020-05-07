package com.isaev.ee.healthcarecrm.selectionfactories;

import javax.persistence.criteria.CriteriaBuilder;

public interface SelectionFactory {
	
	CriteriaBuilder createSelection(SelectionCriteria criteria);

}
