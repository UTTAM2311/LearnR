package com.imaginea.dc.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import libsvm.model.SVMEngine;
import libsvm.svm.model.FeatureNode;
import libsvm.svm.model.SVMModel;
import libsvm.svm.model.SVNProblem;

import com.imaginea.dc.api.IDao;
import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.entities.SVMInput;
import com.imaginea.dc.entities.SVMPredictedValues;
import com.imaginea.dc.service.NewsArticleService;
import com.imaginea.dc.service.PredictService;
import com.imaginea.dc.svmutils.DataPreprocessor;

public class PredictServiceImpl implements PredictService {

	@Autowired
	private NewsArticleService newsArticleService;
	
	@Autowired
	private DataPreprocessor dataPreprocessor;
	
	@Autowired
	private IDao genericDao;
	
	private SVMEngine engine;	
	
	public PredictServiceImpl(){
		engine =  new SVMEngine();
	}
	
	@Override
	@Transactional
	public void startPredictingUnlabelledData(boolean saveSVMInput,
			boolean savePredictedData) {
		// TODO Auto-generated method stub
		List<NewsArticle> newsArticleList = this.fetchNewsArticleForPrediction();
		List<SVMInput> svmInput = this.convertNewsArticleToSVMData(newsArticleList);
		SVMModel model = this.fetchModelFromFile();
		List<SVMInput> svmPredictedValues = this.predict(svmInput, model);
		this.savePredictionToDB(svmPredictedValues);
	}

	@Override
	public List<NewsArticle> fetchNewsArticleForPrediction() {
		// TODO Auto-generated method stub
		List<NewsArticle> newsArticleList = newsArticleService.fetchAllUnlabelledArticles(1, 1000);
		return newsArticleList;
	}

	@Override
	public List<SVMInput> convertNewsArticleToSVMData(
			List<NewsArticle> newsArticleList) {
		// TODO Auto-generated method stub
		List<SVMInput> svmInput = dataPreprocessor.preprocessData(newsArticleList, false);
		return svmInput;
	}

	@Override
	public SVMModel fetchModelFromDB() {
		// TODO Auto-generated method stub
		
		Hashtable<String, Object> criteria = new Hashtable<String, Object> ();
		List model = (List) genericDao.executeResult("svm_model.fetch_latest_model", criteria );
		if (model.size() > 0){
			com.imaginea.dc.entities.SVMModel svmModelObj = (com.imaginea.dc.entities.SVMModel) model.get(0);
			svmModelObj.getModel();
			return null;
		}
		else
			return null;
	}

	@Override
	public List<SVMInput> predict(List<SVMInput> inputData, SVMModel model) {
		// TODO Auto-generated method stub
		
		ArrayList<String> inputFeatures = new ArrayList<String>();
		Iterator<SVMInput> inputDataIterator = inputData.iterator();
		while (inputDataIterator.hasNext()){
			SVMInput input = inputDataIterator.next();
			inputFeatures.add(input.getInputLine());
		}
		ArrayList nodes = this.getFeatureNodes(inputFeatures);
		FeatureNode[][] array = new FeatureNode[nodes.size()][];
		
		ArrayList<Double> predictedValues = new ArrayList<Double>();
		for (int i = 0; i< nodes.size(); i++) {
			ArrayList<FeatureNode> row = (ArrayList<FeatureNode>) nodes.get(i);
			double yTest = engine.svm_predict(model, row.toArray(new FeatureNode[row.size()]));
			predictedValues.add(yTest);
		}
		
		inputDataIterator = inputData.iterator();
		Iterator<Double> predictedIterator = predictedValues.iterator();
		while (inputDataIterator.hasNext() && predictedIterator.hasNext()){
			SVMInput input = inputDataIterator.next();
			Double predictedValue = predictedIterator.next();
			input.setOutputValue(""+predictedValue);
		}
		
		return inputData;		
	}

	@Override	
	public void savePredictionToDB(List<SVMInput> predictedData) {
		// TODO Auto-generated method stub
		Iterator<SVMInput> predictedDataIterator = predictedData.iterator();
		while (predictedDataIterator.hasNext()){
			SVMInput input = predictedDataIterator.next();
			/*input.setCreatedBy("PREDICTED");
			genericDao.save(input);*/
			
			SVMPredictedValues predictedValues = new SVMPredictedValues();
			predictedValues.setNewsArticle(input.getNewsArticle());
			Double outputValue = Double.parseDouble(input.getOutputValue());
			predictedValues.setPredictedValue(outputValue);
			genericDao.save(predictedValues);
		}
	}

	@Override
	public SVMModel fetchModelFromFile() {
		// TODO Auto-generated method stub
		try {
			SVMModel model = engine.svm_load_model("dc.model");
			return model;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private ArrayList getFeatureNodes(ArrayList<String> data){
		ArrayList<ArrayList<FeatureNode>> nodes = new ArrayList<ArrayList<FeatureNode>>();
		for (int i = 0; i < data.size(); i++) {			
			String line = data.get(i);
			ArrayList<FeatureNode> featureNode = this.getFeatureNodeFromLine(line);
			nodes.add(featureNode);
		}
		
		return nodes;
	}
	
	private ArrayList<FeatureNode> getFeatureNodeFromLine(String line){
		String[] values = line.split(" ");
		ArrayList<FeatureNode> featureNode = new ArrayList<FeatureNode>();
		for (int j = 0; j < values.length; j++) {
			FeatureNode node = new FeatureNode();
			if (!values[j].trim().equals("") && values[j].trim().contains(":")) {
				String[] ele = values[j].trim().split(":");
				node.index = Integer.parseInt(ele[0]);
				node.value = Integer.parseInt(ele[1]);

				featureNode.add(node);
			}
		}
		return featureNode;
		
	}

}
