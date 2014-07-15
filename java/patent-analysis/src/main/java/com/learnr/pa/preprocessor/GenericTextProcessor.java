package com.learnr.pa.preprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import textProcessor.options.Options;

public class GenericTextProcessor {

	private List<String> data;
	private List<String> termVector;
	private Map<Integer, String> featureIndex;
	
	public GenericTextProcessor(List<String> data){
		this.data = data;
	}
	
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public List<String> getTermVector() {
		return termVector;
	}
	public void setTermVector(List<String> termVector) {
		this.termVector = termVector;
	}
	
	public void processData(){
		Options options = new Options();
		options.doesStem = true;
		options.verbose = true;
		
		TextProcessorDC textProcessorDC = new TextProcessorDC();	
		textProcessorDC.setTestData(data);
		
		textProcessorDC.buildDocStringArray();
		textProcessorDC.process();
		ArrayList<String> libSvmData = textProcessorDC.generateLIBSVMTrainingDataStringArray();		
		//Map<String, Integer> featureIndex = textProcessorDC.docLabelIDMap;
		Map<Integer, String> featureIndex = textProcessorDC.IDTermMap;
		
		this.setFeatureIndex(featureIndex);
		this.setTermVector(libSvmData);
		
	}

	public Map<Integer, String> getFeatureIndex() {
		return featureIndex;
	}

	public void setFeatureIndex(Map<Integer, String> featureIndex) {
		this.featureIndex = featureIndex;
	}
	
	
}
