package com.isaev.ee.healthcarecrm.selectionfactories.people;

import com.isaev.ee.healthcarecrm.domain.people.Person;
import com.isaev.ee.healthcarecrm.selectionfactories.SelectionFactory;

abstract class PersonSelectionFactory<T extends Person> implements SelectionFactory<T> {

	private final Class<T> typeParameterClass;

	// Constructor

	public PersonSelectionFactory(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}


}
