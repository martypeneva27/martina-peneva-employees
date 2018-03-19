/*
 * EmployeeResult.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees.entities;

/**
 *  EmployeeResult is an entity class. 
 *  It is used for temporary storage during calculations.
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public class EmployeesResult {
	private EmployeeEntity firstEmployee;
	private EmployeeEntity secondEmployee;
	private int daysWorked;
	private long projectId;
	
	public EmployeesResult() {
		this.firstEmployee = null;
		this.secondEmployee = null;
		this.daysWorked = 0;
		this.projectId = 0;
	}
	public EmployeeEntity getFirstEmployee() {
		return firstEmployee;
	}
	public void setFirstEmployee(EmployeeEntity firstEmployee) {
		this.firstEmployee = firstEmployee;
	}
	public EmployeeEntity getSecondEmployee() {
		return secondEmployee;
	}
	public void setSecondEmployee(EmployeeEntity secondEmployee) {
		this.secondEmployee = secondEmployee;
	}
	public int getDaysWorked() {
		return daysWorked;
	}
	public void setDaysWorked(int daysWorked) {
		this.daysWorked = daysWorked;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
}
