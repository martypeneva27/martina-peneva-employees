/*
 * Dates.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *  Dates is an entity class. Contains two parameters:
 *  - startDate
 *  - endDate
 *  and private method convertToMilliseconds(String date).
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public class Dates {
	private long startDate;
	private long endDate;
	
	public Dates(String startDate) {
		if (!startDate.equals("null") && !startDate.equals("NULL") && !startDate.equals("Null")) {
			try {
				this.startDate = this.convertToMilliseconds(startDate);
				this.endDate = new Date().getTime();
			} catch (ParseException e) {
				System.out.println("Wrong date format " + e.getMessage());
				this.startDate = 0;
				this.endDate = 0;
			}
		}
	}
	
	public Dates(String startDate, String endDate) {
		if (!startDate.equals("null") && !startDate.equals("NULL") && !startDate.equals("Null")) {
			try {
				this.startDate = this.convertToMilliseconds(startDate);
				this.endDate = this.convertToMilliseconds(endDate);
				if (this.endDate < this.startDate) {
					long tmp = this.startDate;
					this.startDate = this.endDate;
					this.endDate = tmp;
				}
			} catch (ParseException e) {
				System.out.println("Wrong date format " + e.getMessage());
				this.startDate = 0;
				this.endDate = 0;
			}
		}
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) throws ParseException {
		this.endDate = this.convertToMilliseconds(endDate);
	}

	public long getStartDate() {
		return startDate;
	}
	
	private long convertToMilliseconds(String date) throws ParseException {
		if (date.equals("null") || date.equals("NULL") || date.equals("Null")) {
			return new Date().getTime();
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date result = format.parse(date);
		return result.getTime();
	}
	
}
