package com.learnr.core.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class TextFileUtility {

	private static int dimension=3;

	public static RealMatrix readMatrixFromTextFile(String fileName) {

		List<double[]> points = new ArrayList<double[]>();

		BufferedReader br;
		FileReader fr;
		double[] point = new double[dimension];
		int l = 0;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line;

			// Read lines and put in points List
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] lStrs = line.split("\\s+");
				System.out.println( lStrs[1] + ", " + lStrs[2]);

				// Get point from str[]
				point = getPoint(lStrs);
				points.add(point);

				// counting the dataset size
				l++;
			}

			System.out.println("size of the dataset : " + l);
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  System.out.println(points.size());
		return getDataMatrix(points);
	}

	private static RealMatrix getDataMatrix(List<double[]> points) {
		if (points == null) {
			return null;
		}

		int size = points.size();
		RealMatrix dataMatrix = new Array2DRowRealMatrix(size, dimension);

		for (int i = 0; i < size; i++) {
			dataMatrix.setRow(i, points.get(i));
			System.out.println(dataMatrix.getEntry(i, 0));
		}

		return dataMatrix;
	}

	private static double[] getPoint(String[] lStrs) {

		// TODO fix it later
		int dimension = lStrs.length;
		double[] point = new double[dimension];
		for (int i = 0; i < dimension; i++) {
			point[i] = Double.parseDouble(lStrs[i]);
			System.out.println(point[i]);
		}

		return point;
	}

}
