package com.polymathiccoder.talk.xunit.repository;

import com.polymathiccoder.talk.xunit.domain.Employee;

public interface EmployeeDao {
	void create(final Employee employee);
	Employee readById(final int id); // NOPMD
	void update(final Employee employee);
	void delete(final Employee employee);
}