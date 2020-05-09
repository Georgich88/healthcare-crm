package com.isaev.ee.healthcarecrm.selectionfactories.people;

import java.util.ArrayList;
import java.util.List;

import com.isaev.ee.healthcarecrm.selectionfactories.FilterComparisonType;
import com.isaev.ee.healthcarecrm.selectionfactories.SelectionCriteria;
import com.isaev.ee.healthcarecrm.selectionfactories.SelectionCriterion;

public class PersonSelectionCriteria implements SelectionCriteria {

	private final List<SelectionCriterion<String>> firstName = new ArrayList<SelectionCriterion<String>>();
	private final List<SelectionCriterion<String>> middleName = new ArrayList<SelectionCriterion<String>>();
	private final List<SelectionCriterion<String>> lastName = new ArrayList<SelectionCriterion<String>>();

	public void addFirstNameFilter(FilterComparisonType comparisonType, String filter) {
		firstName.add(new SelectionCriterion<>(comparisonType, filter));		
	}
	
	public void addMiddleNameFilter(FilterComparisonType comparisonType, String filter) {
		middleName.add(new SelectionCriterion<>(comparisonType, filter));		
	}
	
	public void addLastNameFilter(FilterComparisonType comparisonType, String filter) {
		lastName.add(new SelectionCriterion<>(comparisonType, filter));		
	}
	
	@Override
	public boolean isEmpty() {
		return firstName.isEmpty() && middleName.isEmpty() && lastName.isEmpty();
	}

	@Override
	public void reset() {
		firstName.clear();
		middleName.clear();
		lastName.clear();
	}

}
