package com.isaev.ee.healthcarecrm.unitsofwork;

/**
 * A Unit of Work keeps track of every changes during a business transaction
 * that can affect the database. When changes are done, it figures out everything
 * that needs to be done to alter the database as a result of the work.
 * 
 * @author George Isaev
 *
 */
public interface UnitOfWork<T> {

	void registerNew(T object);

	void registerClean(T object);

	void registerDirty(T object);

	void registerRemoved(T object);

	void commit(T object);

	void rollback(T object);

}
