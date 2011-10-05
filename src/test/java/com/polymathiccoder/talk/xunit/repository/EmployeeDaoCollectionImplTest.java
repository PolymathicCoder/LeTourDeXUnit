package com.polymathiccoder.talk.xunit.repository;

import static com.polymathiccoder.talk.xunit.domain.matcher.EmployeeIsEqual.isEmployeeEqual;
import static org.junit.Assert.assertThat;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.LENIENT_ORDER;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.polymathiccoder.talk.xunit.domain.Employee;

public class EmployeeDaoCollectionImplTest {
	private static EmployeeDaoCollectionImpl employeeDao;

	private final static List<Employee> INIT = ImmutableList.of(
		new Employee(1, "Abdelmonaim Remani"), // NOPMD
		new Employee(2, "John Smith"),
		new Employee(3, "Maximino Gomez")); // NOPMD

	private final static List<Employee> AFTER_CREATE = ImmutableList.of(
		new Employee(1, "Abdelmonaim Remani"),
		new Employee(2, "John Smith"),
		new Employee(3, "Maximino Gomez"),
		new Employee(4, "Muhammad Ali"));

	private final static List<Employee> AFTER_UPDATE = ImmutableList.of(
		new Employee(1, "Abdelmonaim Remani"),
		new Employee(2, "John Doe"),
		new Employee(3, "Maximino Gomez"));

	private final static List<Employee> AFTER_DELETE = ImmutableList.of(
		new Employee(1, "Abdelmonaim Remani"),
		new Employee(3, "Maximino Gomez"));

	@Before
	public void setUp() {
		employeeDao = new EmployeeDaoCollectionImpl(INIT);
	}

	@Test
	public void create_aValidEmployee_employeeCreatedInDB() { // NOPMD
		employeeDao.create(new Employee(4, "Muhammad Ali"));
		assertReflectionEquals("The employee was not saved to the database", AFTER_CREATE.toArray(), employeeDao.getEmployees().toArray(), LENIENT_ORDER);
	}

	@Test
	public void readById_noParams_employeeReadFromDB() { // NOPMD
		assertThat("The employee could not be read from the database", employeeDao.readById(1), isEmployeeEqual(new Employee(1, "Abdelmonaim Remani")));

	}

	@Test
	public void update_aValidEmployee_employeeUpdatedInDB() { // NOPMD
		employeeDao.update(new Employee(2, "John Doe"));
		assertReflectionEquals("The employee was not updated in the database", AFTER_UPDATE.toArray(), employeeDao.getEmployees().toArray(), LENIENT_ORDER);
	}

	@Test
	public void delete_aValidEmployee_employeeDeletedFromDB() { // NOPMD
		employeeDao.delete(new Employee(2, "John Smith"));
		assertReflectionEquals("The employee was not deleted from the database", AFTER_DELETE.toArray(), employeeDao.getEmployees().toArray(), LENIENT_ORDER);
	}
}
