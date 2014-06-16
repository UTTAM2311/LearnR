package com.imaginea.dc.svmutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import textProcessor.TextProcessor;
import textProcessor.options.Options;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.entities.SVMInput;
import com.imaginea.dc.service.DataPreProcessingService;
import com.imaginea.dc.svmutils.TextProcessorDC;

public class DataPreprocessor {

	@Autowired
	private DataPreProcessingService dataPreprocessingService;
	
	public List<SVMInput> preprocessData(List<NewsArticle> newsArticle, boolean hasLabel ){
		
		List<SVMInput> svmInput = new ArrayList<SVMInput>();
			
		TextProcessor textProcessor = this.processInput(newsArticle, true, hasLabel, true);
		//textProcessor.SaveResults();
		
		//dataPreprocessingService.saveWordList(textProcessor.wordList);
		//dataPreprocessingService.saveWordCount(textProcessor.wordCnt);
		//dataPreprocessingService.saveSVMInputData(textProcessor.generateLIBSVMTrainingDataStringArray());
	
		List<String>inputStrData = textProcessor.generateLIBSVMTrainingDataStringArray();
		Iterator<String> inputStrDataIterator = inputStrData.iterator();
		Iterator<NewsArticle> newsArticleIterator = newsArticle.iterator();
		while (inputStrDataIterator.hasNext() && newsArticleIterator.hasNext()){
			String inputStr = inputStrDataIterator.next();
			NewsArticle newsArticleData = newsArticleIterator.next();
			
			SVMInput svmInputData = new SVMInput();
			svmInputData.setInputLine(inputStr);
			svmInputData.setNewsArticle(newsArticleData);
			
			svmInput.add(svmInputData);
		}
		
		return svmInput;
	}
	
	private TextProcessor processInput(List<NewsArticle> newsArticle, boolean doesStem, boolean hasLabel, boolean verbose){
		/*boolean doesStem = true;
		boolean hasLabel= true;
		boolean verbose = true; 
		*/
		Options options = new Options();
		options.hasLabel = hasLabel;
		options.doesStem = doesStem;
		options.verbose = verbose;
		
		TextProcessorDC textProcessor = new TextProcessorDC(options);
		textProcessor.setNewsArticleList(newsArticle);
		textProcessor.process();

		return textProcessor;
	}
	
	public HashMap<String, Integer> fetchWordCount(List<NewsArticle> newsArticle){
		TextProcessor textProcessor = this.processInput(newsArticle, true, false, true);
		return textProcessor.wordCnt;
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
