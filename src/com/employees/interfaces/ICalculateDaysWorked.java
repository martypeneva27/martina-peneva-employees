/*
 * ICalculateDaysWorked.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees.interfaces;

import java.util.Map;

import com.employees.entities.EmployeeEntity;

/**
 *  This interface contains declarations of the methods that 
 *  are used for calculations.
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public interface ICalculateDaysWorked {
	public void calculateWorkedDaysAll();
	public void printMaxWorkedDays(Map<Long, EmployeeEntity> employees);
}
