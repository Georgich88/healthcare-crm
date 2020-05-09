package com.isaev.ee.healthcarecrm.selectionfactories;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SelectionCriterion<T> {

	// Criterion attributes

	private final FilterComparisonType comparisonType;
	private final List<T> value;

	// Messages constants

	private static final String MESSAGE_ERROR_NULL_COMPARISON_TYPE = "Comparison type should not be null";
	private static final String MESSAGE_ERROR_NULL_VALUE = "Value should not be null";
	private static final String MESSAGE_ERROR_NULL_VALUE_LIST = "Value list should not be null";
	private static final String MESSAGE_ERROR_EMPTY_VALUE_LIST = "Value list should not be empty";

	// Constructors

	public SelectionCriterion(FilterComparisonType comparisonType, T value) {

		if (comparisonType == null) {
			throw new IllegalArgumentException(MESSAGE_ERROR_NULL_COMPARISON_TYPE);
		}
		if (value == null) {
			throw new IllegalArgumentException(MESSAGE_ERROR_NULL_VALUE);
		}

		List<T> valueList = Collections.unmodifiableList(List.of(value));

		this.comparisonType = comparisonType;
		this.value = valueList;
	}

	public SelectionCriterion(FilterComparisonType comparisonType, List<T> valueList) {

		if (comparisonType == null) {
			throw new IllegalArgumentException(MESSAGE_ERROR_NULL_COMPARISON_TYPE);
		}
		if (valueList == null) {
			throw new IllegalArgumentException(MESSAGE_ERROR_NULL_VALUE_LIST);
		} else if (valueList.isEmpty()) {
			throw new IllegalArgumentException(MESSAGE_ERROR_EMPTY_VALUE_LIST);
		}

		this.comparisonType = comparisonType;

		if (comparisonType.isSingleValueType() && valueList.size() > 1) {
			this.value = valueList.stream().limit(1).collect(Collectors.toUnmodifiableList());
		} else {
			this.value = Collections.unmodifiableList(valueList);
		}
	}

	// Getters
	
	public FilterComparisonType getComparisonType() {
		return comparisonType;
	}

	public List<T> getValue() {
		return value;
	}


	
	// Object inherited methods
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FieldCriterion [comparisonType=")
				.append(comparisonType)
				.append(", value=").append(value)
				.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(comparisonType, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SelectionCriterion other = (SelectionCriterion) obj;
		return comparisonType == other.comparisonType && Objects.equals(value, other.value);
	}
	
}
