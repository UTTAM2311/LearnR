package com.imaginea.dc.service;

import java.util.List;

import libsvm.svm.model.SVMModel;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.entities.SVMInput;

public interface ModelBuildService {

	void startBuildingModel(boolean saveModel);		
	SVMModel buildModel();
	void saveModelToFile(SVMModel model);
		
}
