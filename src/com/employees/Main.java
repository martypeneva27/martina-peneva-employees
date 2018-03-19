/*
 * Main.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.employees.entities.EmployeeEntity;
import com.employees.entities.EntryEntity;

/**
 *  The main class of the program. 
 *  Here are created the data structures. 
 *  The process of reading from input file and fill the data in structures 
 *  is started in the main method of the class.
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public class Main {

	public static void main(String[] args) {
		Reader read = new Reader();
		List<EntryEntity> result = read.convertRecords(read.readFromFile("resources/entities.txt"));
		Projects projects = new Projects();
		Map<Long, EmployeeEntity> employees = new HashMap<Long, EmployeeEntity>();
		
		EntryEntity current = null;
		for (int i = 0; i < result.size(); i++) {
			current = result.get(i);
			if (!employees.containsKey(current.getEmployeeId())) {
				long empID = current.getEmployeeId();
				employees.put(empID, new EmployeeEntity(empID));
			}
			projects.addEmployee(current.getProjectId(), employees.get(current.getEmployeeId()), 
									current.getStartDate(), current.getEndDate());
		}
		projects.calculateWorkedDaysAll();
		projects.printMaxWorkedDays(employees);
	}

}
