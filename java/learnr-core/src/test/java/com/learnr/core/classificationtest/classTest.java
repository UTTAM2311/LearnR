package com.learnr.core.classificationtest;

import java.io.IOException;

import com.learnr.core.classification.LogReg;
import com.learnr.core.classification.dataMapper;

public class classTest {
	public static String filename = "donut.csv";

	// public static String filename1 = "iristest.csv.txt";

	public static void main(String[] args) throws IOException {
		dataMapper data = new dataMapper();

		LogReg.trainModel(data.getVectorsAndTargetValues(filename, 12), 12, 3);
		// LogReg.test(LogReg.convertTestfile(filename1));
	}
}
