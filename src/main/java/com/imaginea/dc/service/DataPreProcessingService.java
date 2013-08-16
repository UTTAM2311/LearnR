package com.imaginea.dc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.imaginea.dc.entities.NewsArticle;

public interface DataPreProcessingService {

	List<NewsArticle> fetchDataFromDB() ;
	void saveWordCount(HashMap<String, Integer> wordCount);
	void saveSVMInputData(ArrayList<String> data);
	void saveWordList(ArrayList<String> wordList);
	ArrayList<String> getSVMInputData();
	ArrayList<String> getOutputValues();
	
}
