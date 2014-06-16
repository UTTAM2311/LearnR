package com.imaginea.dc.service;

import java.util.List;

import libsvm.svm.model.SVMModel;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.entities.SVMInput;

public interface PredictService {

	void startPredictingUnlabelledData(boolean saveSVMInput, boolean savePredictedData);
	List<NewsArticle> fetchNewsArticleForPrediction();
	List<SVMInput> convertNewsArticleToSVMData(List<NewsArticle> newsArticleList);
	SVMModel fetchModelFromDB();
	SVMModel fetchModelFromFile();
	List<SVMInput> predict(List<SVMInput> inputData, SVMModel model);
	void savePredictionToDB(List<SVMInput> predictedData);
	
}
