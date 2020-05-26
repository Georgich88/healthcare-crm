package com.isaev.ee.healthcarecrm.identitymaps;

public interface IdentityMap<K,V> {

	void add(V value);
	
	void add(K key, V value);
	
	V get(K key);
	
}
