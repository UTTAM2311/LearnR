package com.learnr.core.visualization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Matrices {
	private static final Logger logger = LoggerFactory.getLogger(Matrices.class);

	/**
	 * ---Given a list of clusters, converts each cluster to an individual
	 * matrix.
	 * 
	 * @param clusters
	 * @return List of matrices.
	 */
	public static List<RealMatrix> intoMatrices(List<CentroidCluster<Clusterable>> clusters) {

		List<RealMatrix> matrices = null;
		if (clusters == null || clusters.isEmpty())
			return matrices;

		logger.info("size of the clusters" + clusters.size());

		int dimension = getDimension(clusters);
		if (dimension == 0)
			return matrices;

		matrices = new ArrayList<RealMatrix>();

		RealMatrix matrix;
		List<Clusterable> points;
		for (int i = 0; i < clusters.size(); i++) {
			points = clusters.get(i).getPoints();

			// initiate matrix
			matrix = new Array2DRowRealMatrix(clusters.get(i).getPoints().size(), dimension);

			for (int rowindex = 0; rowindex < points.size(); rowindex++) {
				Clusterable point = points.get(rowindex);
				double[] arr = point.getPoint();

				logger.debug("Row vector : " + Arrays.toString(arr));
				matrix.setRow(rowindex, arr);
			}
			matrices.add(matrix);
		}

		return matrices;
	}

	
	
	private static int getDimension(List<CentroidCluster<Clusterable>> clusters) {
		int dimemsion = 0;

		if (clusters == null || clusters.isEmpty())
			return dimemsion;

		for (CentroidCluster<Clusterable> cluster : clusters) {
			if (cluster == null || cluster.getCenter() == null)
				continue;

			Clusterable center = cluster.getCenter();

			return center.getPoint().length;
		}

		return dimemsion;
	}

//	public static void matricesToArray(List<RealMatrix> matrices) {
//		List<List<Integer>> allvalues = new ArrayList<List<Integer>>();
//		for (int i = 0; i < matrices.size(); i++) {
//			if (matrices.get(i) != null) {
//				RealMatrix matrix = matrices.get(i);
//				Matrices.getValuesFromMatrix(matrix);
//			}
//		}
//	}

	public static List<List<Integer>> getValuesFromMatrix(RealMatrix matrix) {
		List<List<Integer>> allvalues = new ArrayList<List<Integer>>();
		for (int index = 0; index < matrix.getRowDimension(); index++) {
			List<Integer> values = new ArrayList<Integer>();
			double[] rowValues = matrix.getRow(index);
			for (int i = 0; i < rowValues.length; i++) {
				values.add(i, (int) rowValues[i]);
			}
			allvalues.add(values);
		}
		return allvalues;
	}
}
