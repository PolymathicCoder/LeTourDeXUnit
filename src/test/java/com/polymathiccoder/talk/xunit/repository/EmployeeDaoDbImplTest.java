package com.polymathiccoder.talk.xunit.repository;

import static com.polymathiccoder.talk.xunit.domain.matcher.EmployeeIsEqual.isEmployeeEqual;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;

import com.polymathiccoder.talk.xunit.domain.Employee;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet(value = {"/init.xml"}, loadStrategy = CleanInsertLoadStrategy.class)
public class EmployeeDaoDbImplTest {
	private static EmployeeDaoDbImpl employeeDao;

	@TestDataSource
	private static DataSource dataSource;

	@Before
	public void setUp() throws SQLException {
		employeeDao = new EmployeeDaoDbImpl(dataSource);
	}

	@Test
	@ExpectedDataSet("/create.xml")
	public void create_aValidEmployee_employeeCreatedInDB() { // NOPMD
		employeeDao.create(new Employee(4, "Muhammad Ali"));
	}

	@Test
	public void readById_noParams_employeeReadFromDB() { // NOPMD
		assertThat(employeeDao.readById(1), isEmployeeEqual(new Employee(1, "Abdelmonaim Remani")));
	}

	@Test
	@ExpectedDataSet("/update.xml")
	public void update_aValidEmployee_employeeUpdatedInDB() { // NOPMD
		employeeDao.update(new Employee(2, "Jhon Doe"));
	}

	@Test
	@ExpectedDataSet("/delete.xml")
	public void delete_aValidEmployee_employeeDeletedFromDB() { // NOPMD
		employeeDao.delete(new Employee(2, "Jhon Smith"));
	}
}
