package com.learnr.plot;

import org.apache.commons.math3.linear.RealMatrix;

import com.learnr.util.TextFileUtility;

public class PlotTest {

	private static final String CLUSTER_DATASET_2D_1 = "C:\\Users\\uttam\\Desktop\\s1.txt";
	private static final String CLUSTER_DATASET_2D_2 = "C:\\Users\\uttam\\Desktop\\s4.txt";
	private static final String CLUSTER_DATASET_2D_3 = "C:\\Users\\uttam\\Desktop\\s2.txt";

	public static void main(String[] args) {
		RealMatrix data = TextFileUtility.readMatrixFromTextFile(CLUSTER_DATASET_2D_1);
		System.out.println(data);
		Plot plott = new Plot();
		plott.for2DimensionPlot(data);

	}
}
