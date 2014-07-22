package com.learnr.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.core.util.ds.LabeledRealMatrix;
import com.learnr.core.util.ds.LabeledRealVector;

/**
 * Odd ball utilities for Real valued DataStructures. Mainly meant for {@link DataSet} and {@link DataPoint}.
 * 
 * @see DataPoint
 * @see DataSet
 * @see LabeledRealVector
 * @see LabeledRealMatrix
 */
public class RealDataUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(RealDataUtils.class);

	/**
	 * Converts a list of {@link DataPoint}s to a double array.
	 * 
	 * @param points
	 *            a List of data points
	 * @return a 2 dimensional <code>double</code> array.
	 */
	public static <CL extends Object> double[][] as2DArray(List<DataPoint<Double, CL>> points) {
		if (points == null || points.isEmpty())
			return null;

		DataPoint<Double, CL> p = points.get(0);
		int dimension = p.dimension();
		int size = points.size();

		double[][] m = new double[size][dimension];
		for (int i = 0; i < points.size(); i++) {
			p = points.get(i);

			// Check point and get array
			if (p != null)
				m[i] = p.asVector().toArray();
		}

		return m;
	}

}
