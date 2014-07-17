package com.learnr.core.plot;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.core.util.ds.LabeledRealMatrixTest;

public class SimplePlotTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LabeledRealMatrixTest.class);
	
	private static RealMatrix data;

	public static void main(String[] args) {
		
		// initialize 
		double[][] d = { { 1, 2 }, { 3, 4 }, {6, 7} };
		data = new Array2DRowRealMatrix(d);
		logger.info(data.toString());

		Plot plot = new Plot(data);
		plot.plotConnectedIn2D("2d plot connected");
		
		plot.clear2Dplot();
		plot.plotScatteredIn2D("2d plot connected");
	}

}
