package com.imaginea.dc.preprocesor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import textProcessor.options.Options;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.service.DataPreProcessingService;
import com.imaginea.dc.svmutils.TextProcessorDC;

public class PreprocessData {

	@Autowired
	private DataPreProcessingService dataPreprocessingService;
	
	public void preprocessData(List<NewsArticle> newsArticle){
		
		boolean doesStem = true;
		boolean hasLabel= true;
		boolean verbose = true; 
		
		Options options = new Options();
		options.hasLabel = hasLabel;
		options.doesStem = doesStem;
		options.verbose = verbose;
		
		TextProcessorDC textProcessor = new TextProcessorDC(options);
		textProcessor.setNewsArticleList(newsArticle);
		textProcessor.process();

		//textProcessor.SaveResults();
		
		dataPreprocessingService.saveWordList(textProcessor.wordList);
		dataPreprocessingService.saveWordCount(textProcessor.wordCnt);
		dataPreprocessingService.saveSVMInputData(textProcessor.generateLIBSVMTrainingDataStringArray());
		
	}
	
	public ArrayList<String> convertStringToSVMInput(ArrayList<String> inputData){
		boolean doesStem = true;
		boolean hasLabel= false;
		boolean verbose = true; 
		
		Options options = new Options();
		options.hasLabel = hasLabel;
		options.doesStem = doesStem;
		options.verbose = verbose;
		
		TextProcessorDC textProcessor = new TextProcessorDC(options);
		textProcessor.setTestData(inputData);
		textProcessor.process();
		ArrayList<String> libSvmData = textProcessor.generateLIBSVMTrainingDataStringArray();
		Iterator<String> libSvmDataIterator = libSvmData.iterator();
		ArrayList<String> svmInputData = new ArrayList<String>();
		while (libSvmDataIterator.hasNext()){
			String data = libSvmDataIterator.next();
			svmInputData.add(data.substring(1).trim());
		}
		return svmInputData;
	} 

}
