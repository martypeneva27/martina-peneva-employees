/*
 * EmployeeEntity.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *  EmployeeEntity is an entity class. 
 *  Contains information about an employee.
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public class EmployeeEntity {
	private long employeeId;
	private Map<EmployeeEntity, Integer> workedWith;
	
	public EmployeeEntity(long employeeId) {
		this.employeeId = employeeId;
		this.workedWith = new HashMap<EmployeeEntity, Integer>();
	}
	
	public long getEmployeeId() {
		return employeeId;
	}

	public Map<EmployeeEntity, Integer> getWorkedWith() {
		return workedWith;
	}
}
