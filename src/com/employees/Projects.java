/*
 * Projects.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.employees.entities.Dates;
import com.employees.entities.EmployeeDates;
import com.employees.entities.EmployeeEntity;
import com.employees.entities.EmployeesResult;
import com.employees.interfaces.ICalculateDaysWorked;
import com.employees.interfaces.IProject;

/**
 *  This class contains the methods that are used for calculation.
 *  It implements the interface for adding data from input file in data structures.
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public class Projects implements IProject, ICalculateDaysWorked {
	private Map<Long, List<EmployeeDates>> projects;

	public Projects() {
		this.projects = new HashMap<Long, List<EmployeeDates>>();
	}

	@Override
	public void addProject(long projectId) {
		this.projects.put(projectId, new ArrayList<EmployeeDates>());
	}

	@Override
	public void addEmployee(long projectId, EmployeeEntity employee, String startDate, String endDate) {
		if (!projects.containsKey(projectId)) {
			this.addProject(projectId);
		}

		List<EmployeeDates> currentProject = this.projects.get(projectId);
		int index = searchEmployee(employee.getEmployeeId(), currentProject);
		if (index == -1) {
			index = currentProject.size();
			currentProject.add(new EmployeeDates(employee));
		}
		currentProject.get(index).getDates().add(new Dates(startDate, endDate));
	}

	private int searchEmployee(long empID, List<EmployeeDates> list) {
		for (int i = 0; i < list.size(); i++) {
			if (empID == list.get(i).getEmployee().getEmployeeId()) {
				return i;
			}
		}
		return -1;
	}


	@Override
	public void calculateWorkedDaysAll() {
		EmployeesResult resultPerProject = new EmployeesResult();

		Set<Entry<Long, List<EmployeeDates>>> entries = this.projects.entrySet();
		for (Entry<Long, List<EmployeeDates>> entry : entries) { // projects
			List<EmployeeDates> allEmp = entry.getValue();
			for (int i = 0; i < allEmp.size() - 1; i++) { // List<EmployeeDates>
				EmployeeDates currentEmp = allEmp.get(i);
				for (int j = i + 1; j < allEmp.size(); j++) {
					EmployeeDates nextEmp = allEmp.get(j);

					if (!currentEmp.getEmployee().getWorkedWith().containsKey(nextEmp.getEmployee())) {
						currentEmp.getEmployee().getWorkedWith().put(nextEmp.getEmployee(), 0);
						nextEmp.getEmployee().getWorkedWith().put(currentEmp.getEmployee(), 0);
					}
					// Calculate the maximum worked days for two employees not depending on project
					calculateTwoEmplDays(currentEmp, nextEmp);

					// Calculate the maximum worked days for two employees depending on project
					int daysPerProject = calculateWorkedDaysPerProject(currentEmp, allEmp.get(j));
					if (resultPerProject.getDaysWorked() < daysPerProject) {
						resultPerProject.setFirstEmployee(currentEmp.getEmployee());
						resultPerProject.setSecondEmployee(nextEmp.getEmployee());
						resultPerProject.setDaysWorked(daysPerProject);
						resultPerProject.setProjectId(entry.getKey());
					}
				}
			}
		}

		System.out.println("---- Max worked days depending on project ----");
		System.out.println("----------------------------------------------");
		System.out.println("EmpID1 = " + resultPerProject.getFirstEmployee().getEmployeeId() + ", EmpID2 = "
				+ resultPerProject.getSecondEmployee().getEmployeeId() + ", ProjectId = "
				+ resultPerProject.getProjectId() + ", workedDays = " + resultPerProject.getDaysWorked());
		System.out.println("--------------------------------------------------");
	}

	private void calculateTwoEmplDays(EmployeeDates firstEmployee, EmployeeDates secondEmployee) {
		for (int i = 0; i < firstEmployee.getDates().size(); i++) {
			Dates current = firstEmployee.getDates().get(i);
			for (int j = 0; j < secondEmployee.getDates().size(); j++) {
				Dates next = secondEmployee.getDates().get(j);
				// If employees hadn't work together
				if (current.getStartDate() > next.getEndDate() || current.getEndDate() < next.getStartDate()) {
					continue;
				}
				long start = current.getStartDate() >= next.getStartDate() ? current.getStartDate()
						: next.getStartDate();
				long end = current.getEndDate() >= next.getEndDate() ? next.getEndDate() : current.getEndDate();
				int days = (int) ((end - start) / (1000 * 60 * 60 * 24));
				Map<EmployeeEntity, Integer> firstWorkedWith = firstEmployee.getEmployee().getWorkedWith();
				Map<EmployeeEntity, Integer> secondWorkedWith = secondEmployee.getEmployee().getWorkedWith();
				days = days + firstWorkedWith.get(secondEmployee.getEmployee());
				firstWorkedWith.put(secondEmployee.getEmployee(), days);
				secondWorkedWith.put(firstEmployee.getEmployee(), days);
			}
		}
	}

	private int calculateWorkedDaysPerProject(EmployeeDates firstEmployee, EmployeeDates secondEmployee) {
		int days = 0;
		for (int i = 0; i < firstEmployee.getDates().size(); i++) {
			Dates current = firstEmployee.getDates().get(i);
			for (int j = 0; j < secondEmployee.getDates().size(); j++) {
				Dates next = secondEmployee.getDates().get(j);
				// If employees hadn't work together
				if (current.getStartDate() > next.getEndDate() || current.getEndDate() < next.getStartDate()) {
					continue;
				}
				long start = current.getStartDate() >= next.getStartDate() ? current.getStartDate()
						: next.getStartDate();
				long end = current.getEndDate() >= next.getEndDate() ? next.getEndDate() : current.getEndDate();
				days = days + (int) ((end - start) / (1000 * 60 * 60 * 24));
			}
		}
		return days;
	}

	@Override
	public void printMaxWorkedDays(Map<Long, EmployeeEntity> employees) {
		EmployeesResult result = new EmployeesResult();

		Set<Map.Entry<Long, EmployeeEntity>> entries = employees.entrySet();
		for (Map.Entry<Long, EmployeeEntity> entry : entries) {
			EmployeeEntity current = entry.getValue();
			Set<EmployeeEntity> set = new HashSet<EmployeeEntity>(current.getWorkedWith().keySet());
			for (Iterator<EmployeeEntity> iterator = set.iterator(); iterator.hasNext();) {
				EmployeeEntity elem = iterator.next();
				if (result.getDaysWorked() == 0 || result.getDaysWorked() < elem.getWorkedWith().get(current)) {
					result.setFirstEmployee(current);
					result.setSecondEmployee(elem);
					result.setDaysWorked(elem.getWorkedWith().get(current));
				}
			}
		}
		System.out.println();
		System.out.println("---- Max worked days not depending on project ----");
		System.out.println("--------------------------------------------------");
		System.out.println("EmpID1 = " + result.getFirstEmployee().getEmployeeId() + ", " + "EmpID2 = "
				+ result.getSecondEmployee().getEmployeeId() + ", " + "workedDays = " + result.getDaysWorked());
		System.out.println("--------------------------------------------------");
	}

}
