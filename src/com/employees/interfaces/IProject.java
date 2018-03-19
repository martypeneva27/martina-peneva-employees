/*
 * IProject.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees.interfaces;

import com.employees.entities.EmployeeEntity;

/**
 *  This interface contains declarations of the methods that 
 *  are used for filling data structures with data.
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public interface IProject {
	public void addProject(long projectId);
	public void addEmployee(long projectId, EmployeeEntity employee, String startDate, String endDate);
}
