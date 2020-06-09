package com.isaev.ee.healthcarecrm.domain.schedule;

import java.time.LocalDateTime;

/**
 * Contains the period between two time stamps.
 * 
 * @author Georgy Isaev
 *
 */
public class DateTimeRange {
	
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
	public DateTimeRange() {
	}
	
	public DateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}  
    
}
