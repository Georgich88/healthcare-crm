package com.isaev.ee.healthcarecrm.selectionfactories;

public enum FilterComparisonType {
	
	CONTAINS,
	EQUAL,
	GREATER,
	GREATER_OR_EQUAL,
	IN_HIERARCHY,
	IN_LIST,
	IN_LIST_BY_HIERARCHY,
	LESS,
	LESS_OR_EQUAL,
	NOT_CONTAINS,
	NOT_EQUAL,
	NOT_IN_HIERARCHY,
	NOT_IN_LIST,
	NOT_IN_LIST_BY_HIERARCHY;
	
	public boolean isSingleValueType() {		
		return !isMultipleValueType();		
	}
	
	public boolean isMultipleValueType() {
		if (this.equals(IN_LIST) && this.equals(IN_LIST_BY_HIERARCHY) && this.equals(NOT_IN_LIST)
				&& this.equals(NOT_IN_LIST_BY_HIERARCHY)) {
			return true;
		} else {
			return false;
		}
	}
	
}
