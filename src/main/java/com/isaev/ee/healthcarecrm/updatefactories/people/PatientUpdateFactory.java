package com.isaev.ee.healthcarecrm.updatefactories.people;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.isaev.ee.healthcarecrm.domain.people.Patient;
import com.isaev.ee.healthcarecrm.selectionfactories.SelectionCriteria;
import com.isaev.ee.healthcarecrm.selectionfactories.people.PatientSelectionCriteria;

@Component
public class PatientUpdateFactory extends PersonUpdateFactory<Patient> {

	private static final String MESSAGE_ERROR_ILLEGAL_SELECTION_CRITERIA_TYPE = "Illegal selection criteria type, PatientSelectionCriteria is expected";
	
	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;

	public PatientUpdateFactory() {
		this(Patient.class);
	}

	public PatientUpdateFactory(Class<Patient> typeParameterClass) {
		super(typeParameterClass);
	}

	@Transactional
	public void update(PatientSelectionCriteria criteria, BiConsumer<CriteriaUpdate<Patient>, Root<Patient>> updateAction) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
		CriteriaUpdate<Patient> criteriaQuery = builder.createCriteriaUpdate(Patient.class);
		Root<Patient> root = criteriaQuery.from(Patient.class);

		criteria.getFirstName().forEach(filterItem -> {
			addUpdateFilterForStringField(builder, criteriaQuery, root, filterItem, "firstName");
		});

		criteria.getMiddleName().forEach(filterItem -> {
			addUpdateFilterForStringField(builder, criteriaQuery, root, filterItem, "middleName");
		});

		criteria.getLastName().forEach(filterItem -> {
			super.addUpdateFilterForStringField(builder, criteriaQuery, root, filterItem, "lastName");
		});

		updateAction.accept(criteriaQuery, root);
		entityManager.joinTransaction();
		entityManager.createQuery(criteriaQuery).executeUpdate();
		entityManager.close();

	}

	@Override
	@Transactional
	public void update(SelectionCriteria criteria, Consumer<CriteriaUpdate<Patient>> updateAction) {
		if (!(criteria instanceof PatientSelectionCriteria))
			throw new IllegalArgumentException(MESSAGE_ERROR_ILLEGAL_SELECTION_CRITERIA_TYPE);
		update((PatientSelectionCriteria) criteria, updateAction);
	}


}
