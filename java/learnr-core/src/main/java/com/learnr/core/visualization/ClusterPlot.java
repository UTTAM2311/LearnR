package com.learnr.core.visualization;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.math.plot.Plot2DPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClusterPlot {
	private static final Logger logger = LoggerFactory.getLogger(ClusterPlot.class);

	/**
	 * plots 2D visualization of each matrices Xth colomn feature vs Yth column
	 * features
	 * 
	 * @param matrices
	 * @param xColIndex
	 * @param yColIndex
	 */

	public static void plotting(List<RealMatrix> matrices, int xColIndex, int yColIndex) {
		if (xColIndex == yColIndex)
			throw new RuntimeException("X and Y Column indexes should be different");

		Plot2DPanel plot = new Plot2DPanel();
		for (int i = 0; i < matrices.size(); i++) {
			RealMatrix m = matrices.get(i);

			// Get Data
			double[] xData = m.getColumn(xColIndex);
			double[] yData = m.getColumn(yColIndex);
			// Plot data

			plot.addScatterPlot("Cluster Plot", getColor(i), xData, yData);
			plot.setAxisLabels("x-axis", "y-axis");
		}

		JFrame frame = new JFrame("2D PLOT");
		frame.setContentPane(plot);
		frame.setSize(800, 600);
		frame.setLocation(200, 150);
		frame.setVisible(true);
	}

	private static Color getColor(int i) {
		if (i == 0)
			return Color.ORANGE;
		if (i == 1)
			return Color.GREEN;
		if (i == 2)
			return Color.RED;
		if (i == 3)
			return Color.CYAN;
		if (i == 4)
			return Color.DARK_GRAY;
		if (i == 5)
			return Color.YELLOW;
		return Color.BLUE;
	}

	@SuppressWarnings("null")
	public static void clustersPlot(List<CentroidCluster<Clusterable>> clusters, int xColIndex, int yColIndex) {
		CentroidCluster<Clusterable> cluster;
		Plot2DPanel plot = new Plot2DPanel();
		for (int i = 0; i < clusters.size(); i++) {
			cluster = clusters.get(i);
			if (cluster != null || cluster.getPoints() != null)
				plot = plottings(convertToMatrix(cluster), xColIndex, yColIndex, getColor(i));

		}
		JFrame frame = new JFrame("2D PLOT");
		frame.setContentPane(plot);
		frame.setSize(800, 600);
		frame.setLocation(200, 150);
		frame.setVisible(true);
	}

	private static RealMatrix convertToMatrix(CentroidCluster<Clusterable> cluster) {
		Clusterable centerpoint = cluster.getCenter();
		List<Clusterable> points = cluster.getPoints();
		RealMatrix matrix = generatematrix(centerpoint, points);
		return matrix;
	}

	private static Plot2DPanel plottings(RealMatrix matrix, int xColIndex, int yColIndex, Color color) {
		double[] xdata = matrix.getColumn(xColIndex);
		double[] ydata = matrix.getColumn(yColIndex);
		return plot(xdata, ydata, color);
	}

	private static Plot2DPanel plot(double[] xdata, double[] ydata, Color color) {
		Plot2DPanel plot = new Plot2DPanel();
		for (int i = 0; i < xdata.length; i++) {
			double[] xvalues = { xdata[0], xdata[i] };
			double[] yvalues = { ydata[0], ydata[i] };
			plot.addLinePlot("clusterplot", color, xvalues, yvalues);
		}
		return plot;
	}

	private static RealMatrix generatematrix(Clusterable centerpoint, List<Clusterable> points) {
		RealMatrix matrix = new Array2DRowRealMatrix(points.size() + 1, centerpoint.getPoint().length);
		matrix.setRow(0, centerpoint.getPoint());
		for (int i = 0; i < points.size(); i++) {
			matrix.setRow(i + 1, points.get(i).getPoint());
		}
		return matrix;
	}

	// public static void plot(Clusterable centerpoint, List<Clusterable>
	// points) {
	// // TODO Auto-generated method stub
	// double
	// }

	/**
	 * Plots 2D visualizations for all the
	 * 
	 * @param listOfMatrix
	 */
	public static void allPlots(List<RealMatrix> matrices) {
		System.out.println(matrices.size());
		for (int index = 0; index < matrices.size(); index++) {
			for (int i = 0; i < matrices.get(index).getColumnDimension(); i++) {
				for (int j = 0; j < matrices.get(index).getColumnDimension(); j++) {
					if ((i > j) && (j < 3)) {
						ClusterPlot.plotting(matrices, i, j);
						System.out.println(i + " " + j);
					}
				}
			}
		}
	}
}