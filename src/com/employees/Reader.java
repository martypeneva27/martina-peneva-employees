/*
 * Reader.java	0.01 19/03/2018
 *
 * Copyright (c) Martina Peneva
 */

package com.employees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.employees.entities.EntryEntity;
import com.employees.interfaces.IReader;

/**
 *  This class is used for reading from input file.
 *  It also converts some of the input fields to the type that is needed.
 *
 * @version 0.01 19 Mar 2018  
 * @author Martina Peneva
 */

public class Reader implements IReader {

	@Override
	public List<String> readFromFile(String filePath) {
		List<String> records = new ArrayList<String>();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filePath));
				String line;
				while ((line = reader.readLine()) != null) {
					records.add(line);
				}
				reader.close();
				return records;
			} catch (Exception e) {
				System.err.format("Exception occurred trying to read '%s'.", filePath);
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public List<EntryEntity> convertRecords(List<String> entry) {
		List<EntryEntity> entries = new ArrayList<EntryEntity>();
		int count = 0;
		while (entry.size() > count) {
			entries.add(new EntryEntity(entry.get(count)));
			count++;
		}
		return entries;
	}
}
