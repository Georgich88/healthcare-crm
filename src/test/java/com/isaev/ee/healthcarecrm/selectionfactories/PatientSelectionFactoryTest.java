package com.isaev.ee.healthcarecrm.selectionfactories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.isaev.ee.healthcarecrm.domain.people.Patient;
import com.isaev.ee.healthcarecrm.selectionfactories.people.PatientSelectionCriteria;
import com.isaev.ee.healthcarecrm.selectionfactories.people.PatientSelectionFactory;

@SpringBootTest
public class PatientSelectionFactoryTest {

	@Autowired
	private PatientSelectionFactory selectionFactory;
	
	
	@Test
	void shouldFindPatientsWithNamesContains() {

		PatientSelectionCriteria criteria = new PatientSelectionCriteria();

		// Find all patients with "an" in first name
		criteria.addFirstNameFilter(FilterComparisonType.CONTAINS, "%an%");
		List<Patient> patientsFirstNameFiltered = selectionFactory.select(criteria);
		assertEquals(2, patientsFirstNameFiltered.size());

	}
	
	@Test
	void shouldFindPatientsWithNamesNotContains() {

		PatientSelectionCriteria criteria = new PatientSelectionCriteria();

		// Find all patients with "an" in first name
		criteria.addFirstNameFilter(FilterComparisonType.NOT_CONTAINS, "%an%");
		List<Patient> patientsFirstNameFiltered = selectionFactory.select(criteria);
		assertEquals(7, patientsFirstNameFiltered.size());

	}
	
	@Test
	void shouldFindPatientsWithNamesEqual() {

		PatientSelectionCriteria criteria = new PatientSelectionCriteria();

		// Find all Georges
		criteria.addFirstNameFilter(FilterComparisonType.EQUAL, "George");
		List<Patient> patientsFirstNameFiltered = selectionFactory.select(criteria);
		assertEquals(2, patientsFirstNameFiltered.size());

	}
	
	@Test
	void shouldFindPatientsWithNamesNotEqual() {

		PatientSelectionCriteria criteria = new PatientSelectionCriteria();

		// Find all non-Georges
		criteria.addFirstNameFilter(FilterComparisonType.NOT_EQUAL, "George");
		List<Patient> patientsFirstNameFiltered = selectionFactory.select(criteria);
		assertEquals(7, patientsFirstNameFiltered.size());

	}
	
	@Test
	void shouldFindPatientsWithNamesInList() {

		PatientSelectionCriteria criteria = new PatientSelectionCriteria();

		// Find Barrow and Pacquiao
		criteria.addLastNameFilter(FilterComparisonType.IN_LIST, List.of("Barrow","Pacquiao"));
		List<Patient> patientsLastNameFiltered = selectionFactory.select(criteria);
		assertEquals(2, patientsLastNameFiltered.size());

	}
	
	@Test
	void shouldFindPatientsWithNamesNotInList() {

		PatientSelectionCriteria criteria = new PatientSelectionCriteria();

		// Find others than Barrow and Pacquiao
		criteria.addLastNameFilter(FilterComparisonType.NOT_IN_LIST, List.of("Barrow","Pacquiao"));
		List<Patient> patientsLastNameFiltered = selectionFactory.select(criteria);
		assertEquals(7, patientsLastNameFiltered.size());

	}

}
