package com.isaev.ee.healthcarecrm.selectionfactories;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaBuilder.In;

public interface SelectionFactory<T> {
	
	List<T> select(SelectionCriteria criteria);
	
	default void addSelectionFilterForStringField(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root,
			SelectionCriterion<String> filterItem, final String filedName) {
		final var comparisonType = filterItem.getComparisonType();
		final List<String> valueList = filterItem.getValue();
		final String value = valueList.get(0);
		if (comparisonType.equals(FilterComparisonType.CONTAINS)) {
			query.select(root).where(builder.like(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.NOT_CONTAINS)) {
			query.select(root).where(builder.not(builder.like(root.get(filedName), value)));
		} else if (comparisonType.equals(FilterComparisonType.EQUAL)) {
			query.select(root).where(builder.equal(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.NOT_EQUAL)) {
			query.select(root).where(builder.not(builder.equal(root.get(filedName), value)));
		} else if (comparisonType.equals(FilterComparisonType.LESS)) {
			query.select(root).where(builder.greaterThan(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.LESS_OR_EQUAL)) {
			query.select(root).where(builder.greaterThanOrEqualTo(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.LESS)) {
			query.select(root).where(builder.greaterThan(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.LESS_OR_EQUAL)) {
			query.select(root).where(builder.greaterThanOrEqualTo(root.get(filedName), value));
		} else if (comparisonType.equals(FilterComparisonType.IN_LIST)) {
			In<String> inClause = builder.in(root.get(filedName));
			valueList.forEach(inClause::value);
			query.select(root).where(inClause);
		} else if (comparisonType.equals(FilterComparisonType.NOT_IN_LIST)) {
			In<String> inClause = builder.in(root.get(filedName));
			valueList.forEach(inClause::value);
			query.select(root).where(builder.not(inClause));
		}
	}

}
