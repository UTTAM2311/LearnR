package com.learnr.plot;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;

public class PlotTest {

	private static final String CLUSTER_DATASET_2D_1 = "C:\\Users\\uttam\\Desktop\\pathbased.txt";
	// private static final String CLUSTER_DATASET_2D_2 =
	// "C:\\Users\\uttam\\Desktop\\s4.txt";
	// private static final String CLUSTER_DATASET_2D_3 =
	// "C:\\Users\\uttam\\Desktop\\s2.txt";
	static String plotname = "indicate the x axis vs y axis";

	@Test
	public void simpleTest() {
		Plot plott = new Plot();

		double[][] d = { { 1, 2 }, { 3, 4 } };
		RealMatrix dMatrix = new Array2DRowRealMatrix(d);
		System.out.println(dMatrix);
		plott.for2DimensionPlot(dMatrix, "test plot");
	}

	@Test
	public void simpleTest2() {
		Plot plott = new Plot();
		double[][] d2 = { { 1, 5 }, { 3, 20 } };
		RealMatrix dMatrix2 = new Array2DRowRealMatrix(d2);
		System.out.println(dMatrix2);
		plott.for2DimensionPlot(dMatrix2, "test plot");
	}

	@Test
	public void simpleTest3() {
		Plot plott = new Plot();
		double[][] d5 = { { 1, 5, 3 }, { 3, 20, 56 } };
		RealMatrix dMatrix3 = new Array2DRowRealMatrix(d5);
		System.out.println(dMatrix3);
		plott.for3DimensionPlot(dMatrix3, "test plot");

	}

	@Test
	public void simpleTest4() {
		Plot plott = new Plot();
		RealMatrix data = com.learnr.util.TextFileUtility.readMatrixFromTextFile(CLUSTER_DATASET_2D_1);
		plott.for3DimensionPlot(data, "test plot");
	}

}
