package com.polymathiccoder.talk.xunit.domain.matcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import com.polymathiccoder.talk.xunit.domain.Employee;

public class EmployeeIsEqual extends TypeSafeMatcher<Employee> {
		private transient final Employee employee;
		
		private transient final Matcher<? super Integer> idMatcher;
		private transient final Matcher<? super String> nameMatcher;
		
		public EmployeeIsEqual(final Employee employee) {
			super();
			this.employee = employee;
			
			idMatcher = is(equalTo(employee.getId()));
			nameMatcher = is(equalTo(employee.getName()));
		}
		
		@Override
		public boolean matchesSafely(final Employee employee) {
			return idMatcher.matches(employee.getId()) &&
				nameMatcher.matches(employee.getName()) ;
		}

		public void describeTo(final Description description) {
			description.appendText(employee.toString());
		}
		
		@Factory
		public static <T> Matcher<Employee> isEmployeeEqual(final Employee employee) {
			return new EmployeeIsEqual(employee);
		}
	}