package com.isaev.ee.healthcarecrm.selectionfactories.people;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.isaev.ee.healthcarecrm.domain.people.Patient;
import com.isaev.ee.healthcarecrm.selectionfactories.SelectionCriteria;

@Component
public class PatientSelectionFactory extends PersonSelectionFactory<Patient> {

	private static final String MESSAGE_ERROR_ILLEGAL_SELECTION_CRITERIA_TYPE = "Illegal selection criteria type, PatientSelectionCriteria is expected";
	
	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;

	public PatientSelectionFactory() {
		this(Patient.class);
	}

	public PatientSelectionFactory(Class<Patient> typeParameterClass) {
		super(typeParameterClass);
	}

	public List<Patient> select(PatientSelectionCriteria criteria) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
		CriteriaQuery<Patient> criteriaQuery = builder.createQuery(Patient.class);
		Root<Patient> root = criteriaQuery.from(Patient.class);

		criteria.getFirstName().forEach(filterItem -> {
			addSelectionFilterForStringField(builder, criteriaQuery, root, filterItem, "firstName");
		});

		criteria.getMiddleName().forEach(filterItem -> {
			addSelectionFilterForStringField(builder, criteriaQuery, root, filterItem, "middleName");
		});

		criteria.getLastName().forEach(filterItem -> {
			super.addSelectionFilterForStringField(builder, criteriaQuery, root, filterItem, "lastName");
		});

		var resultList = entityManager.createQuery(criteriaQuery).getResultList();
		entityManager.close();

		return resultList;
	}

	@Override
	public List<Patient> select(SelectionCriteria criteria) {
		if (!(criteria instanceof PatientSelectionCriteria))
			throw new IllegalArgumentException(MESSAGE_ERROR_ILLEGAL_SELECTION_CRITERIA_TYPE);
		return select((PatientSelectionCriteria) criteria);
	}

}
