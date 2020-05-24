package com.isaev.ee.healthcarecrm.updatefactories.people;

import com.isaev.ee.healthcarecrm.domain.people.Person;
import com.isaev.ee.healthcarecrm.updatefactories.UpdateFactory;

abstract class PersonUpdateFactory<T extends Person> implements UpdateFactory<T> {

	private final Class<T> typeParameterClass;

	// Constructor

	public PersonUpdateFactory(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

}
