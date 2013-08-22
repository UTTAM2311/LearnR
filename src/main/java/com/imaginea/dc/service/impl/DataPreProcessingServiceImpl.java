package com.imaginea.dc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.imaginea.dc.api.IDao;
import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.entities.SVMInput;
import com.imaginea.dc.entities.WordCount;
import com.imaginea.dc.service.DataPreProcessingService;
import com.imaginea.dc.service.NewsArticleService;

public class DataPreProcessingServiceImpl implements DataPreProcessingService {

	@Autowired
	private IDao dao;
		
	@Autowired
	private NewsArticleService newsArticleService;
	
	public List<NewsArticle> fetchDataFromDB() {
		// TODO Auto-generated method stub
		return newsArticleService.fetchArticlesForTraining();
		//return dao.findAllEntities(NewsArticle.class);		
	}

	@Transactional
	public void saveWordCount(HashMap<String, Integer> wordCount) {
		// TODO Auto-generated method 
		dao.executeResult("word_count.deleteall", new Hashtable());
		Iterator<Entry<String, Integer>> entrySetIterator = wordCount.entrySet().iterator();
		while (entrySetIterator.hasNext()){
			Entry<String, Integer> entry = entrySetIterator.next();
			WordCount wordCountObj = new WordCount();
			wordCountObj.setCount(entry.getValue());
			wordCountObj.setWord(entry.getKey());
			dao.save(wordCountObj);
		}
	}

	@Transactional
	public void saveSVMInputData(ArrayList<String> data) {
		// TODO Auto-generated method stub
		dao.executeResult("svm_input.deleteall", new Hashtable());
		Iterator<String> dataIterator = data.iterator();
		while (dataIterator.hasNext()){
			String svmData = dataIterator.next();
			String outputVal= ""+svmData.charAt(0);
			SVMInput input = new SVMInput();
			input.setInputLine(svmData.substring(1).trim());
			input.setOutputValue(outputVal);
			dao.save(input);
		}
	}

	public void saveWordList(ArrayList<String> wordList) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<String> getSVMInputData() {
		// TODO Auto-generated method stub
		Hashtable criteria = new Hashtable();
		ArrayList<String> data = (ArrayList<String>) dao.getEntities(SVMInput.class, "svm_input.fetchinputline", criteria);		
		return data;
	}

	public ArrayList<String> getOutputValues() {
		// TODO Auto-generated method stub
		Hashtable criteria = new Hashtable();
		ArrayList<String> data = (ArrayList<String>) dao.getEntities(SVMInput.class, "svm_input.fetchoutpuvalues", criteria);		
		return data;
	}

	
	
}
