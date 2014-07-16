package com.learnr.util.ds;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LabeledRealMatrixTest {

	private static final Logger logger = LoggerFactory.getLogger(LabeledRealMatrixTest.class);

	private double[][] d = { { 1, 2 }, { 3, 4 } };
	private List<String> rowLabels;
	private List<String> columnLabels;

	private String R1 = "R1";
	private String R2 = "R2";
	private String C1 = "C1";
	private String C2 = "C2";

	@Before
	public void init() {

		// Row labels
		rowLabels = new ArrayList<>();
		rowLabels.add(R1);
		rowLabels.add(R2);

		// Column labels
		columnLabels = new ArrayList<>();
		columnLabels.add(C1);
		columnLabels.add(C2);

	}

	/* --- Constructor with labels and data --- */

	@Test(expected = IllegalArgumentException.class)
	public void test_CONSTRUCTOR_null_row_labels() {
		LabeledMatrix<String, String> lm = new LabeledRealMatrix<>(null, columnLabels, d);
		logger.info("Labeled Matrix : " + lm);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_CONSTRUCTOR_null_column_labels() {
		LabeledMatrix<String, String> lm = new LabeledRealMatrix<>(rowLabels, null, d);
		logger.info("Labeled Matrix : " + lm);
	}

	@Test(expected = NullArgumentException.class)
	public void test_CONSTRUCTOR_null_data() {
		LabeledMatrix<String, String> lm = new LabeledRealMatrix<>(rowLabels, columnLabels, null);
		logger.info("Labeled Matrix : " + lm);
	}

	@Test(expected = DimensionMismatchException.class)
	public void test_CONSTRUCTOR_row_dimension_mismatch() {
		rowLabels.add("New row Label");
		logger.info("Labels dimension : " + rowLabels.size() + " X " + columnLabels.size());

		LabeledMatrix<String, String> lm = new LabeledRealMatrix<>(rowLabels, columnLabels, d);
		logger.info("Labeled Matrix : " + lm);
	}

	@Test(expected = DimensionMismatchException.class)
	public void test_CONSTRUCTOR_column_dimension_mismatch() {
		columnLabels.add("New row Label");
		logger.info("Labels dimension : " + rowLabels.size() + " X " + columnLabels.size());

		LabeledMatrix<String, String> lm = new LabeledRealMatrix<>(rowLabels, columnLabels, d);
		logger.info("Labeled Matrix : " + lm);
	}
	
	
	/* --- getRowLabels --- */

	@Test
	public void test_getRowLabels_size() {
		int size = rowLabels.size();

		LabeledMatrix<String, String> lm = new LabeledRealMatrix<>(rowLabels, columnLabels, d);
		logger.info("Labeled Matrix : " + lm);

		List<String> labels = lm.getRowLabels();

		Assert.assertEquals(size, labels.size());
		Assert.assertEquals(rowLabels, labels);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void test_getRowLabels_immutable() {
		LabeledMatrix<String, String> lm = new LabeledRealMatrix<>(rowLabels, columnLabels, d);
		logger.info("Labeled Matrix : " + lm);

		lm.getRowLabels().add("new String");
	}
	
	
	/* --- getColumnLabels --- */
	
	@Test
	public void test_getColumnLabels_size() {
		int size = columnLabels.size();
		
		LabeledMatrix<String, String> lm = new LabeledRealMatrix<>(rowLabels, columnLabels, d);
		logger.info("Labeled Matrix : " + lm);
		
		List<String> labels = lm.getColumnLabels();
		
		Assert.assertEquals(size, labels.size());
		Assert.assertEquals(columnLabels, labels);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void test_getColumnLabels_immutable() {
		LabeledMatrix<String, String> lm = new LabeledRealMatrix<>(rowLabels, columnLabels, d);
		logger.info("Labeled Matrix : " + lm);

		lm.getColumnLabels().add("new String");
	}
	
	

}
