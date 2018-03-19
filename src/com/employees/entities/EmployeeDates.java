/*
 * EmployeeDates.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *  EmployeeDates is an entity class. 
 *  It is used to combine information about employee and periods of work.
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public class EmployeeDates {
	private EmployeeEntity employee;
	private List<Dates> dates;
	
	public EmployeeDates(EmployeeEntity employee) {
		this.employee = employee;
		this.dates = new ArrayList<Dates>();
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public List<Dates> getDates() {
		return dates;
	}
	
}
