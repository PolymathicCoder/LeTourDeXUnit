package com.polymathiccoder.talk.xunit.domain;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

public class QuadraticEquationTest {
	@Test
	public void solve_valuesYieldingANegativeDiscriminant_noRealSolutionCalculated() { // NOPMD
		final List<Double> actual = new QuadraticEquation(5.0, 2.0, 1.0).solve();
		assertThat("The method should have returned 0 solutions", actual.size(), equalTo(0));
	}

	@Test
	public void solve_valuesYieldingADiscriminantEqualToZero_oneRealSolutionCalculated() { // NOPMD
	    final List<Double> actual = new QuadraticEquation(9.0, 12.0, 4.0).solve();
		assertThat("The method should not have returned exactly 1 solution", actual.size(), equalTo(1));
		assertTrue("The method returned the wrong solution", actual.contains(-0.67));
	}

	@Test
	public void solve_valuesYieldingAPositiveDiscriminant_twoRealSolutionsCalculated() { // NOPMD
	    final List<Double> actual = new QuadraticEquation(1.0, 5.0, 6.0).solve();
		assertThat("The method should not have returned exactly 2 solution", actual.size(), equalTo(2));
		assertTrue("The method returned the wrong solutions", actual.containsAll(asList(-2.0, -3.0)));
	}

	@Test
	public void caculateDiscriminant_valuesYieldingANegativeDiscriminant_calculated() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException { // NOPMD
	    final Method method = QuadraticEquation.class.getDeclaredMethod("caculateDiscriminant");
		method.setAccessible(true);
		assertThat("The method returned the wrong discriminant", (Double) method.invoke(new QuadraticEquation(5.0, 2.0, 1.0)), equalTo(-16.0));
	}

	@Test
	public void caculateDiscriminant_valuesYieldingADiscriminantEqualToZero_calculated() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException { // NOPMD
	    final Method method = QuadraticEquation.class.getDeclaredMethod("caculateDiscriminant");
		method.setAccessible(true);
		assertThat("The method returned the wrong discriminant", (Double) method.invoke(new QuadraticEquation(9.0, 12.0, 4.0)), equalTo(0.0));
	}

	@Test
	public void caculateDiscriminant_valuesYieldingAPositiveDiscriminant_calculated() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException { // NOPMD
	    final Method method = QuadraticEquation.class.getDeclaredMethod("caculateDiscriminant");
		method.setAccessible(true);
		assertThat("The method returned the wrong discriminant", (Double) method.invoke(new QuadraticEquation(1.0, 5.0, 6.0)), equalTo(1.0));
	}
}
