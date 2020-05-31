package com.isaev.ee.healthcarecrm.identitymaps;

/**
 * @author Ensures that each object gets loaded only once by keeping every
 *         loaded object in a map.
 *
 * @param <K> - the key for the map.
 * @param <V> - the object for the map.
 */
public interface IdentityMap<K, V> {

	void add(V value);

	void add(K key, V value);

	V get(K key);

}
