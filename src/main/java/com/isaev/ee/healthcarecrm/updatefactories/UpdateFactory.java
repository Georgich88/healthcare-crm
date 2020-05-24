package com.isaev.ee.healthcarecrm.updatefactories;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaBuilder.In;

import com.isaev.ee.healthcarecrm.selectionfactories.FilterComparisonType;
import com.isaev.ee.healthcarecrm.selectionfactories.SelectionCriteria;
import com.isaev.ee.healthcarecrm.selectionfactories.SelectionCriterion;

public interface UpdateFactory<T> {
	
	void update(SelectionCriteria criteria, Consumer<CriteriaUpdate<T>> updateAction);

	default void addUpdateFilterForStringField(CriteriaBuilder builder, CriteriaUpdate<T> update, Root<T> root,
			SelectionCriterion<String> filterItem, final String filedName) {
		final var comparisonType = filterItem.getComparisonType();
		final List<String> valueList = filterItem.getValue();
		final String value = valueList.get(0);
		if (comparisonType.equals(FilterComparisonType.CONTAINS)) {
			update.where(builder.like(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.NOT_CONTAINS)) {
			update.where(builder.not(builder.like(root.get(filedName), value)));
		} else if (comparisonType.equals(FilterComparisonType.EQUAL)) {
			update.where(builder.equal(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.NOT_EQUAL)) {
			update.where(builder.not(builder.equal(root.get(filedName), value)));
		} else if (comparisonType.equals(FilterComparisonType.LESS)) {
			update.where(builder.greaterThan(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.LESS_OR_EQUAL)) {
			update.where(builder.greaterThanOrEqualTo(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.LESS)) {
			update.where(builder.greaterThan(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.LESS_OR_EQUAL)) {
			update.where(builder.greaterThanOrEqualTo(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.IN_LIST)) {
			In<String> inClause = builder.in(root.get(filedName));
			valueList.forEach(inClause::value);
			update.where(inClause);
		} else if (comparisonType.equals(FilterComparisonType.NOT_IN_LIST)) {
			In<String> inClause = builder.in(root.get(filedName));
			valueList.forEach(inClause::value);
			update.where(builder.not(inClause));
		}
	}
	
}
