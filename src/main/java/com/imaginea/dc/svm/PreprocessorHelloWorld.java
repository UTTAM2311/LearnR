package com.imaginea.dc.svm;

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

		textProcessor.verbose = verbose;

		textProcessor.process();

		textProcessor.SaveResults();
		
	}

}
