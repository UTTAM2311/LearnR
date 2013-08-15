package com.imaginea.dc.svm;

import java.util.ArrayList;

import textProcessor.TextProcessor;

public class PreprocessorHelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String workSpacePath = "C:\\Users\\prasannav\\git\\DeathCluster\\data";
		String mergedFileName = "testdatainput.txt";
		String dataDirName = "output";
		String ext = "txt";
		boolean doesStem = true;
		boolean hasLabel= false;
		boolean verbose = true; 
		
		TextProcessor textProcessor = new TextProcessor(workSpacePath, mergedFileName, dataDirName, ext, hasLabel, doesStem);

		ArrayList<String> strArray = new ArrayList<String>();
		strArray.add("0	This is Test Positive");
		strArray.add("0	This is also Positive");
		strArray.add("1	This is Negative Test");
		strArray.add("1	This is nothing but Negative");
		
		ArrayList<String> testStr = textProcessor.generateLIBSVMTestDataStringArray(strArray, hasLabel);
		
		System.out.println(testStr);
		
		textProcessor.verbose = verbose;

		textProcessor.process();

		textProcessor.SaveResults();
		
	}

}
