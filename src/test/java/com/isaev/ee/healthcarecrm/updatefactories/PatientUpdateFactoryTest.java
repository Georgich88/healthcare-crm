package com.isaev.ee.healthcarecrm.updatefactories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.isaev.ee.healthcarecrm.domain.people.Patient;
import com.isaev.ee.healthcarecrm.selectionfactories.FilterComparisonType;
import com.isaev.ee.healthcarecrm.selectionfactories.people.PatientSelectionCriteria;
import com.isaev.ee.healthcarecrm.selectionfactories.people.PatientSelectionFactory;
import com.isaev.ee.healthcarecrm.updatefactories.people.PatientUpdateFactory;

@SpringBootTest
public class PatientUpdateFactoryTest {

	private static final String MIDDLE_NAME_UPDATED = "updated";
	
	@Autowired
	private PatientUpdateFactory updateFactory;
	@Autowired
	private PatientSelectionFactory selectionFactory;	
	
	@Test
	void shouldUpdatePatientsWithNamesContains() {

		PatientSelectionCriteria criteria = new PatientSelectionCriteria();

		// Update all patients with "an" in first name
		criteria.addFirstNameFilter(FilterComparisonType.CONTAINS, "%an%");
		updateFactory.update(criteria, (criteriaUpdate, root) -> {
			criteriaUpdate.set(root.get("middleName"), MIDDLE_NAME_UPDATED + " shouldUpdatePatientsWithNamesContains");
		});

		// Find all patients with "an" in first name
		criteria.addFirstNameFilter(FilterComparisonType.CONTAINS, "%an%");
		List<Patient> patientsFirstNameFiltered = selectionFactory.select(criteria);
		patientsFirstNameFiltered.forEach(patient -> assertEquals(patient.getMiddleName(),
				MIDDLE_NAME_UPDATED + " shouldUpdatePatientsWithNamesContains"));
		
	}
	
	@Test
	void shouldUpdatePatientsWithNamesNotContains() {

		PatientSelectionCriteria criteria = new PatientSelectionCriteria();

		// Update all patients with "an" in first name
		criteria.addFirstNameFilter(FilterComparisonType.NOT_CONTAINS, "%an%");
		updateFactory.update(criteria, (criteriaUpdate, root) -> {
			criteriaUpdate.set(root.get("middleName"), MIDDLE_NAME_UPDATED + " shouldUpdatePatientsWithNamesNotContains");
		});
		
		// Find all patients with "an" in first name
		criteria.addFirstNameFilter(FilterComparisonType.NOT_CONTAINS, "%an%");
		List<Patient> patientsFirstNameFiltered = selectionFactory.select(criteria);
		assertEquals(7, patientsFirstNameFiltered.size());
		patientsFirstNameFiltered.forEach(patient -> assertEquals(patient.getMiddleName(),
				MIDDLE_NAME_UPDATED + " shouldUpdatePatientsWithNamesNotContains"));

	}

	@Test
	void shouldUpdatePatientsWithNamesEqual() {

		PatientSelectionCriteria criteria = new PatientSelectionCriteria();

		// Update all Georges
		criteria.addFirstNameFilter(FilterComparisonType.EQUAL, "George");
		updateFactory.update(criteria, (criteriaUpdate, root) -> {
			criteriaUpdate.set(root.get("middleName"), MIDDLE_NAME_UPDATED + " shouldUpdatePatientsWithNamesEqual");
		});
		
		// Find all Georges
		criteria.addFirstNameFilter(FilterComparisonType.EQUAL, "George");
		List<Patient> patientsFirstNameFiltered = selectionFactory.select(criteria);
		assertEquals(2, patientsFirstNameFiltered.size());
		patientsFirstNameFiltered.forEach(patient -> assertEquals(patient.getMiddleName(),
				MIDDLE_NAME_UPDATED + " shouldUpdatePatientsWithNamesEqual"));


	}

}
