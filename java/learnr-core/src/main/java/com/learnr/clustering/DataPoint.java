package com.learnr.clustering;

import org.apache.commons.math3.ml.clustering.Clusterable;

public class DataPoint implements Clusterable {
	private double[] point;

	public DataPoint(double[] p) {
		this.point = p;
	}
/**
 * returns point
 */
	public double[] getPoint() {
		return point;
	}

	@Override
	/**
	 * method used to convert strings to points
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (double p : point) {
			sb.append(p).append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}
