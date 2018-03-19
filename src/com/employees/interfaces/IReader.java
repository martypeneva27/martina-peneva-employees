/*
 * IProject.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees.interfaces;

import java.util.List;

import com.employees.entities.EntryEntity;

/**
 *  This interface contains declarations of the methods that 
 *  are used for reading and converting data from input file.
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public interface IReader {
	public List<String> readFromFile(String fileName);
	public List<EntryEntity> convertRecords(List<String> entry);
}
