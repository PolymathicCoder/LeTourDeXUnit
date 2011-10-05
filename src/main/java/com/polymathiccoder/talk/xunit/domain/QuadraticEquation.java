package com.polymathiccoder.talk.xunit.domain;

import static java.lang.Math.pow;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class QuadraticEquation {
	private final static DecimalFormat TWO_DECIMAL_FORM = new DecimalFormat("#.##");

	private transient final double a; // NOPMD
	private transient final double b; // NOPMD
	private transient final double c; // NOPMD

	public QuadraticEquation(final double a, final double b, final double c) { // NOPMD
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public List<Double> solve() {
		final double discriminant = caculateDiscriminant();
		final List<Double> list = new ArrayList<Double>();

		if (discriminant == 0) {
			list.add(Double.valueOf(TWO_DECIMAL_FORM.format(-b / (2 * a))));
		} else if (discriminant > 0) {
		    list.add(Double.valueOf(TWO_DECIMAL_FORM.format((-b - discriminant) / (2 * a))));
		    list.add(Double.valueOf(TWO_DECIMAL_FORM.format((-b + discriminant) / (2 * a))));
		}
		return list;
	}

	private double caculateDiscriminant() {
		return Double.valueOf(TWO_DECIMAL_FORM.format(pow(b, 2) - 4 * a * c));
	}
}
