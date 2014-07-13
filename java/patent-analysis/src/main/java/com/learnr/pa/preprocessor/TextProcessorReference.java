package com.learnr.pa.preprocessor;

import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

import textProcessor.options.Options;
import edu.stanford.nlp.neural.NeuralUtils;

public class TextProcessorReference {
	
	public static void processText(){
		String text = "1";
		SimpleMatrix simpleMatrix = NeuralUtils.convertTextMatrix(text);
		System.out.println(simpleMatrix);
	}
	
	public static void main(String args[]){
		//processText();
		String text1 = "This is a sample text";
		String text2 = "This is another text text babu";
		
		List<String> input = new ArrayList<>();
		input.add(text2);
		input.add(text1);
		
		Options options = new Options();
		options.doesStem = true;
		options.verbose = true;
		
		TextProcessorDC textProcessorDC = new TextProcessorDC();	
		textProcessorDC.setTestData(input);
		
		textProcessorDC.buildDocStringArray();
		textProcessorDC.process();
		ArrayList<String> libSvmData = textProcessorDC.generateLIBSVMTrainingDataStringArray();
		//textProcessorDC.docIDMap
		System.out.println(libSvmData);
	}
	
}
