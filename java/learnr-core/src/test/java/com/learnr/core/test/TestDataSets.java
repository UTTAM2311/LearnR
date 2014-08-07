package com.learnr.core.test;

import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.core.plot.Plot;

public class TestDataSets {

	private static final Logger logger = LoggerFactory.getLogger(TestDataSets.class);

	// data sets paths
	private static final String DS_DIR = "/numeric_datasets/";

	private static final String D2_DS1_PATH = DS_DIR + "2d_ds1.txt";
	private static final String D2_DS2_PATH = DS_DIR + "2d_ds2.txt";
	private static final String D2_DS3_PATH = DS_DIR + "2d_ds3.txt";
	private static final String D3_DS1_PATH = DS_DIR + "3d_ds1_compound.txt";
	private static final String D3_DS2_PATH = DS_DIR + "3d_ds2_pathbased.txt";
	private static final String D3_DS3_PATH = DS_DIR + "3d_ds3_spiral.txt";
	private static final String D32_DS1_PATH = DS_DIR + "32d_ds1.txt";
	private static final String D64_DS1_PATH = DS_DIR + "64d_ds1.txt";
	private static final String D128_DS1_PATH = DS_DIR + "128d_ds1.txt";

	private static CSVReadUtil csvUtil = new CSVReadUtil();

	
	/* --- Test Cases --- */

	@Test
	public void test_readCsvFile() {
		RealMatrix m = csvUtil.readCSVfileAsMatrix(D2_DS1_PATH);
		Assert.assertNotNull(m);
	}

	@Test
	public void test_2d_ds1_dataset_by_plot() throws InterruptedException {
		RealMatrix m = get2dDataSetSample1();
		Plot plot = new Plot(m);
		plot.plotScatteredIn2D("2d plot scattered");

		// Put thread to sleep to see the plot
		Thread.sleep(10000);
	}

	
	/* --- Datasets --- */

	public static RealMatrix get2dDataSetSample1() {
		RealMatrix m = csvUtil.readCSVfileAsMatrix(D2_DS1_PATH);
		Assert.assertNotNull(m);
		Assert.assertEquals(2, m.getColumnDimension());

		logger.info("Returning 2D Test DataSet 1 - Size : " + m.getRowDimension());
		return m;
	}

	public static RealMatrix get2dDataSetSample2() {
		RealMatrix m = csvUtil.readCSVfileAsMatrix(D2_DS2_PATH);
		Assert.assertNotNull(m);
		Assert.assertEquals(2, m.getColumnDimension());
		
		logger.info("Returning 2D Test DataSet 2 - Size : " + m.getRowDimension());
		return m;
	}

	public static RealMatrix get2dDataSetSample3() {
		RealMatrix m = csvUtil.readCSVfileAsMatrix(D2_DS3_PATH);
		Assert.assertNotNull(m);
		Assert.assertEquals(2, m.getColumnDimension());

		logger.info("Returning 2D Test DataSet 3 - Size : " + m.getRowDimension());
		return m;
	}

	public static RealMatrix get3dDataSetSample1() {
		RealMatrix m = csvUtil.readCSVfileAsMatrix(D3_DS1_PATH);
		Assert.assertNotNull(m);
		Assert.assertEquals(3, m.getColumnDimension());
		
		logger.info("Returning 3D Test DataSet 1 (compound) - Size : " + m.getRowDimension());
		return m;
	}

	public static RealMatrix get3dDataSetSample2() {
		RealMatrix m = csvUtil.readCSVfileAsMatrix(D3_DS2_PATH);
		Assert.assertNotNull(m);
		Assert.assertEquals(3, m.getColumnDimension());
		
		logger.info("Returning 3D Test DataSet 1 (pathbased) - Size : " + m.getRowDimension());
		return m;
	}

	public static RealMatrix get3dDataSetSample3() {
		RealMatrix m = csvUtil.readCSVfileAsMatrix(D3_DS3_PATH);
		Assert.assertNotNull(m);
		Assert.assertEquals(3, m.getColumnDimension());
		
		logger.info("Returning 3D Test DataSet 1 (spiral) - Size : " + m.getRowDimension());
		return m;
	}

	public static RealMatrix get32dDataSetSample1() {
		RealMatrix m = csvUtil.readCSVfileAsMatrix(D32_DS1_PATH);
		Assert.assertNotNull(m);
		Assert.assertEquals(32, m.getColumnDimension());
		
		logger.info("Returning 32D Test DataSet 1 - Size : " + m.getRowDimension());
		return m;
	}

	public static RealMatrix get64dDataSetSample1() {
		RealMatrix m = csvUtil.readCSVfileAsMatrix(D64_DS1_PATH);
		Assert.assertNotNull(m);
		Assert.assertEquals(64, m.getColumnDimension());
		
		logger.info("Returning 64D Test DataSet 1 - Size : " + m.getRowDimension());
		return m;
	}

	public static RealMatrix get128dDataSetSample1() {
		RealMatrix m = csvUtil.readCSVfileAsMatrix(D128_DS1_PATH);
		Assert.assertNotNull(m);
		Assert.assertEquals(128, m.getColumnDimension());
		
		logger.info("Returning 128D Test DataSet 1 - Size : " + m.getRowDimension());
		return m;
	}

}
