package com.learnr.core.plot;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.core.util.ds.LabeledRealMatrixTest;

public class PlotTest {

	private static final Logger logger = LoggerFactory.getLogger(LabeledRealMatrixTest.class);

	private double[][] d = { { 1, 2 }, { 3, 4 }, { 6, 7 } };
	private RealMatrix data;

	@Before
	public void init() {
		data = new Array2DRowRealMatrix(d);

	}

	@Test
	public void test2Dplot() throws InterruptedException {
		Plot plot = new Plot(data);
		plot.plotConnectedIn2D("2d plot connected");

	}

	@After
	public void destroy() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
