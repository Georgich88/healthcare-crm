package com.isaev.ee.healthcarecrm.selectionfactories;

/**
 * Determines the types of comparison which can be used in the selection
 * criteria.
 * 
 * @author Georgy Isaev
 *
 */
public enum FilterComparisonType {

	CONTAINS,
	EQUAL,
	GREATER,
	GREATER_OR_EQUAL,
	IN_LIST,
	LESS,
	LESS_OR_EQUAL,
	NOT_CONTAINS,
	NOT_EQUAL,
	NOT_IN_LIST;
	
	public boolean isSingleValueType() {		
		return !isMultipleValueType();		
	}
	
	public boolean isMultipleValueType() {
		if (this.equals(IN_LIST) || this.equals(NOT_IN_LIST)) {
			return true;
		} else {
			return false;
		}
	}
	
}
