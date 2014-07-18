package com.learnr.core.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.core.util.Verify;

public class CSVReadUtil {

	private static final Logger logger = LoggerFactory.getLogger(CSVReadUtil.class);

	public static final String COMMA = ",";

	public RealMatrix readCSVfileAsMatrix(String fileName) {
		Verify.notNull(fileName);

		File file = getClassPathResourseFile(fileName);
		if (file == null)
			return null;

		List<double[]> points = new ArrayList<double[]>();

		BufferedReader br;
		FileReader fr;
		int l = 0;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String line;
			double[] point;

			// Read lines and put in points List
			while ((line = br.readLine()) != null) {
				String[] lStrs = line.split(COMMA);

				// Get point from str[]
				point = getPoint(lStrs);
				points.add(point);

				// counting the dataset size
				l++;
			}

			logger.info("size of the dataset : " + l);
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		RealMatrix dataMatrix = getDataMatrix(points);
		return dataMatrix;
	}

	private static RealMatrix getDataMatrix(List<double[]> points) {
		Verify.notEmpty(points);

		int size = points.size();
		int dimension = points.get(0).length;

		// Initialize a new matrix
		RealMatrix dataMatrix = new Array2DRowRealMatrix(size, dimension);
		for (int i = 0; i < size; i++) {
			dataMatrix.setRow(i, points.get(i));
		}

		return dataMatrix;
	}

	private static double[] getPoint(String[] lStrs) {
		int dimension = lStrs.length;
		double[] point = new double[dimension];
		for (int i = 0; i < dimension; i++) {
			point[i] = Double.parseDouble(lStrs[i]);
		}
		return point;
	}

	private File getClassPathResourseFile(String fileName) {
		logger.info("Attempting to read class path file : " + fileName);

		// Get the file path/url
		URL url = getClass().getResource(fileName);
		if (url == null) {
			return null;
		}

		File file = new File(url.getPath());
		return file;
	}

}
