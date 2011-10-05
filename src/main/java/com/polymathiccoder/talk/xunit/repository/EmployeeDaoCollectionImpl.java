package com.polymathiccoder.talk.xunit.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import com.google.common.collect.ImmutableList;
import com.polymathiccoder.talk.xunit.domain.Employee;

public class EmployeeDaoCollectionImpl implements EmployeeDao {
	private transient final List<Employee> employees;

	public EmployeeDaoCollectionImpl(final List<Employee> employees) {
		this.employees = new ArrayList<Employee>();
		//Deep copy
	    for(Employee employee: employees) {
	    	this.employees.add(SerializationUtils.clone(employee));
	    }
	}

	public ImmutableList<Employee> getEmployees() {
		return ImmutableList.copyOf(employees);
	}

	@Override
	public void create(final Employee employee) {
		employees.add(employee);
	}

	@Override
	public Employee readById(final int id) { // NOPMD
	    Employee found = null; // NOPMD
	    for (Employee employee : employees) {
			if (employee.getId() == id) {
			    found = employee; // NOPMD
			}
		}
		return found;
	}

	@Override
	public void update(final Employee employee) {
		readById(employee.getId()).setName(employee.getName());
	}

	@Override
	public void delete(final Employee employee) {
		employees.remove(employee);
	}
}
