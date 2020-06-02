package com.isaev.ee.healthcarecrm.unitsofwork;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaev.ee.healthcarecrm.dao.schedule.SlotDao;
import com.isaev.ee.healthcarecrm.dao.schedule.TimetableDao;
import com.isaev.ee.healthcarecrm.domain.schedule.Timetable;

/**
 * @author George Isaev
 *
 */
@Component
public class TimetableUnitOfWork implements UnitOfWork<Timetable> {

	@Autowired
	private TimetableDao timetableDao;
	private Set<Timetable> newTimetables = Collections.synchronizedSet(new HashSet<Timetable>());
	private Set<Timetable> dirtyTimetables = Collections.synchronizedSet(new HashSet<Timetable>());
	private Set<Timetable> removedTimetables = Collections.synchronizedSet(new HashSet<Timetable>());

	@Override
	public void registerNew(Timetable timetable) {
		if (timetable == null) {
			throw new IllegalArgumentException(MESSAGE_ERROR_NEW_OBJECT_SHOULD_NOT_BE_NULL);
		}
		if (dirtyTimetables.contains(timetable)) {
			throw new IllegalArgumentException(MESSAGE_ERROR_CANNOT_REGISTER_DIRTY_OBJECT_AS_NEW);
		}
		if (removedTimetables.contains(timetable)) {
			throw new IllegalArgumentException(MESSAGE_ERROR_CANNOT_REGISTER_REMOVED_OBJECT_AS_NEW);
		}
		newTimetables.add(timetable);
	}

	@Override
	public void registerClean(Timetable timetable) {
		if (timetable == null) {
			throw new IllegalArgumentException(MESSAGE_ERROR_OBJECT_SHOULD_NOT_BE_NULL);
		}
	}

	@Override
	public void registerDirty(Timetable timetable) {
		if (timetable == null) {
			throw new IllegalArgumentException(MESSAGE_ERROR_OBJECT_SHOULD_NOT_BE_NULL);
		}
		if (dirtyTimetables.contains(timetable)) {
			throw new IllegalArgumentException(MESSAGE_ERROR_CANNOT_REGISTER_DIRTY_OBJECT_AS_NEW);
		}

	}

	@Override
	public void registerRemoved(Timetable timetable) {
		if (timetable == null) {
			throw new IllegalArgumentException(MESSAGE_ERROR_OBJECT_SHOULD_NOT_BE_NULL);
		}

	}

	@Override
	public void commit() {
		synchronized (this) {
			insertNew();
			updateDirty();
			deleteRemoved();
		}
	}
	
	@Override
	public void rollback() {
		synchronized (this) {
			newTimetables.clear();
			dirtyTimetables.clear();
			removedTimetables.clear();
		}
	}


	@Transactional
	private void insertNew() {
		newTimetables.forEach(timetableDao::save);
	}

	@Transactional
	private void updateDirty() {
		dirtyTimetables.forEach(timetableDao::update);
	}

	@Transactional
	private void deleteRemoved() {
		removedTimetables.forEach(timetableDao::delete);
	}

}
