package com.imaginea.dc.preprocesor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import textProcessor.options.Options;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.service.DataPreProcessingService;
import com.imaginea.dc.svm.TextProcessorDC;

public class PreprocessData {

	@Autowired
	private DataPreProcessingService dataPreprocessingService;
	
	public void preprocessData(){
		List<NewsArticle>newsArticle = dataPreprocessingService.fetchDataFromDB();
		
		// Properties which will not be used anyhow. Data read from DB.
		String workSpacePath = "C:\\Users\\prasannav\\git\\DeathCluster\\data";
		String mergedFileName = "testdatainput.txt";
		String dataDirName = "output";
		String ext = "txt";
		
		boolean doesStem = true;
		boolean hasLabel= true;
		boolean verbose = true; 
		
		Options options = new Options();
		options.workSpacePath = workSpacePath;
		options.mergedFileName = mergedFileName;
		options.dataDirName = dataDirName;
		options.ext = ext;
		options.hasLabel = hasLabel;
		options.doesStem = doesStem;
		options.verbose = verbose;
		
		TextProcessorDC textProcessor = new TextProcessorDC(options);
		textProcessor.setNewsArticleList(newsArticle);
		textProcessor.process();

		textProcessor.SaveResults();
		
		dataPreprocessingService.saveWordList(textProcessor.wordList);
		dataPreprocessingService.saveWordCount(textProcessor.wordCnt);
		dataPreprocessingService.saveSVMInputData(textProcessor.generateLIBSVMTrainingDataStringArray());
		
	}

}
