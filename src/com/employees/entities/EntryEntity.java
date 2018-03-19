/*
 * EntryEntity.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees.entities;

/**
 *  EntryEntity is an entity class. 
 *  It is used to keep partially parsed data from input file.
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public class EntryEntity {
	private long employeeId;
	private long projectId;
	private String startDate;
	private String endDate;
	
	public EntryEntity(String entryRow) {
		String[] cols = entryRow.split(", ");
		this.employeeId = Long.parseLong(cols[0]);
		this.projectId = Long.parseLong(cols[1]);
		this.startDate = cols[2];
		this.endDate = cols.length > 3 ? cols[3] : null;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public long getProjectId() {
		return projectId;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
}
