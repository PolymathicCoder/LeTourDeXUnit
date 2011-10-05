package com.polymathiccoder.talk.xunit.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polymathiccoder.talk.xunit.domain.Employee;

public class EmployeeDaoDbImpl implements EmployeeDao {
	private static final String SQL_CREATE = "INSERT INTO tbl_employee(id, name) VALUES(?, ?)";
	private static final String SQL_READ_BY = "SELECT * FROM tbl_employee WHERE id = ?";
	private static final String SQL_UPDATE = "UPDATE tbl_employee SET name = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM tbl_employee WHERE id = ?";

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDaoDbImpl.class); 
	
    private transient final DataSource dataSource;

	public EmployeeDaoDbImpl(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(final Employee employee) {
		try {
		    final PreparedStatement statement = dataSource.getConnection().prepareStatement(SQL_CREATE);
			statement.setInt(1, employee.getId());
			statement.setString(2, employee.getName());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			LOG.error("Could not create the record in the database");
		}
	}

	@Override
	public Employee readById(final int id) { // NOPMD
		Employee employee = null; // NOPMD
		try {
		    final PreparedStatement statement = dataSource.getConnection().prepareStatement(SQL_READ_BY);
			statement.setInt(1, id);
			final ResultSet resultSet = statement.executeQuery(); // NOPMD
			if (resultSet.next()) {
			    employee = new Employee(resultSet.getInt("id"), resultSet.getString("name"));
			}
		} catch (SQLException sqlException) {
			LOG.error("Could not read the record from the database");
		}
		return employee;
	}

	@Override
	public void update(final Employee employee) {
		try {
		    final PreparedStatement statement = dataSource.getConnection().prepareStatement(SQL_UPDATE);
			statement.setInt(2, employee.getId());
			statement.setString(1, employee.getName());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			LOG.error("Could not update the record in the database");
		}
	}

	@Override
	public void delete(final Employee employee) {
		try {
		    final PreparedStatement statement = dataSource.getConnection().prepareStatement(SQL_DELETE);
			statement.setInt(1, employee.getId());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			LOG.error("Could not delete the record from the database");
		}
	}
}
