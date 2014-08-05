package com.learnr.core.classificationtest;

import java.io.IOException;

import com.learnr.core.classification.LogReg;
import com.learnr.core.classification.dataMapper;

public class classTest {
	public static String filename = " wine.data";
	public static String filename1 = "iristest.csv.txt";

	public static void main(String[] args) throws IOException {
		dataMapper data = new dataMapper();
		LogReg model = new LogReg();

		LogReg.trainModel(data.getVectorsAndTargetValues(filename, 13), 13, 50);

	//LogReg.test(LogReg.convertTestfile(filename1));
	}
}
