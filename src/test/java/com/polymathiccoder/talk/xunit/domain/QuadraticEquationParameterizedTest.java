package com.polymathiccoder.talk.xunit.domain; // NOPMD

import static java.util.Arrays.asList;
import static org.fest.reflect.core.Reflection.method;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.LENIENT_ORDER;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class QuadraticEquationParameterizedTest {
	private final double a; // NOPMD
	private final double b; // NOPMD
	private final double c; // NOPMD
	private final double expectedDiscriminant; // NOPMD
	private double[] expectedSolutions; // NOPMD

	public QuadraticEquationParameterizedTest(final double a, final double b, final double c, final double expectedDiscriminant, final double... expectedSolutions) { // NOPMD
		this.a = a;
		this.b = b;
		this.c = c;
		this.expectedDiscriminant = expectedDiscriminant;
		this.expectedSolutions = expectedSolutions;
	}

	@Parameters
	public static Collection<Object[]> parametersAndExpected() {
		return asList(new Object[][] {
				{1.0, 5.0, 6.0, 1, new double[] {-2.0, -3.0}},
				{5.0, 2.0, 1.0, -16.0, new double[] {}},
				{9.0, 12.0, 4.0, 0, new double[] {-0.67}}
		});
	}

	@Test
	public void solve_valuesYieldingDiscriminantsOfAllSigns_realSolutionsCalculatedIfAvailable() { // NOPMD
		final List<Double> actual = new QuadraticEquation(a, b, c).solve();
		assertReflectionEquals("The method rerturned the wrong solutions", expectedSolutions, actual.toArray(), LENIENT_ORDER);
	}

	@Test
	public void caculateDiscriminant_valuesYieldingDiscriminantsOfAllSigns_calculated() { // NOPMD
		final double discriminant = method("caculateDiscriminant")
			.withReturnType(double.class)
			.in(new QuadraticEquation(a, b, c))
			.invoke();
		assertThat("The method rerturned the wrong discriminant", discriminant, equalTo(expectedDiscriminant));
	}
}
