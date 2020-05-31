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
	
	static final String MESSAGE_ERROR_CANNOT_REGISTER_NEW_OBJECT_AS_REMOVED = "Cannot register new object as removed";
	static final String MESSAGE_ERROR_CANNOT_REGISTER_DIRTY_OBJECT_AS_REMOVED = "Cannot register dirty object as removed";
	static final String MESSAGE_ERROR_CANNOT_REGISTER_NEW_OBJECT_AS_DIRTY = "Cannot register new object as dirty";
	static final String MESSAGE_ERROR_CANNOT_REGISTER_REMOVED_OBJECT_AS_DIRTY = "Cannot register removed object as dirty";
	static final String MESSAGE_ERROR_CANNOT_REGISTER_REMOVED_OBJECT_AS_NEW = "Cannot register removed object as new";
	static final String MESSAGE_ERROR_CANNOT_REGISTER_DIRTY_OBJECT_AS_NEW = "Cannot register dirty object as new";
	static final String MESSAGE_ERROR_NEW_OBJECT_SHOULD_NOT_BE_NULL = "New object should not be null";	
	static final String MESSAGE_ERROR_OBJECT_SHOULD_NOT_BE_NULL = "Object should not be null";
	
	void registerNew(T object) throws Exception;

	void registerClean(T object) throws Exception;

	void registerDirty(T object) throws Exception;

	void registerRemoved(T object) throws Exception;

	void commit() throws Exception;

	void rollback() throws Exception;

}
