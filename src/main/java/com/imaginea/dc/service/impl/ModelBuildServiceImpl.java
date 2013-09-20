package com.imaginea.dc.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import libsvm.model.SVMEngine;
import libsvm.svm.model.FeatureNode;
import libsvm.svm.model.SVMModel;
import libsvm.svm.model.SVMParams;
import libsvm.svm.model.SVNProblem;

import com.imaginea.dc.api.IDao;
import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.entities.SVMInput;
import com.imaginea.dc.service.ModelBuildService;
import com.imaginea.dc.service.NewsArticleService;
import com.imaginea.dc.svmutils.DataPreprocessor;

public class ModelBuildServiceImpl implements ModelBuildService {

	@Autowired
	private NewsArticleService newsArticleService;
	
	@Autowired
	private DataPreprocessor dataPreprocessor;
	
	@Autowired
	private IDao genericDao;
	
	private int trainingPercentage = 70;
	
	private SVMEngine engine;
	
	public ModelBuildServiceImpl(){
		engine = new SVMEngine();
	}
	
	
	@Override
	public void startBuildingModel(boolean saveModel) {
		// TODO Auto-generated method stub

		List<NewsArticle> newsArticleList = this.fetchNewsArticleForBuildingModel();
		List<SVMInput> svmInput = this.convertNewsArticleToSVMData(newsArticleList);
		SVMModel model = this.buildModel(svmInput);
		this.saveModelToFile(model);
		
	}

	@Override
	public List<NewsArticle> fetchNewsArticleForBuildingModel() {
		// TODO Auto-generated method stub
		List<NewsArticle> newsArticleList = newsArticleService.fetchArticlesForTraining();
		return newsArticleList;
	}

	@Override
	public List<SVMInput> convertNewsArticleToSVMData(
			List<NewsArticle> newsArticleList) {
		// TODO Auto-generated method stub
		List<SVMInput> svmInput = dataPreprocessor.preprocessData(newsArticleList, true);
		return svmInput;
	}

	@Override
	public SVMModel buildModel(List<SVMInput> inputData) {
		// TODO Auto-generated method stub
		
		SVNProblem problem = new SVNProblem();
		
		
		ArrayList<String> inputFeatures = new ArrayList<String>();
		ArrayList<String> outputVals = new ArrayList<String>();
		
		Iterator<SVMInput> inputDataIterator = inputData.iterator();
		while (inputDataIterator.hasNext()){
			SVMInput input = inputDataIterator.next();
			inputFeatures.add(input.getInputLine());
			outputVals.add(input.getOutputValue());
		}
		
		ArrayList nodes = this.getFeatureNodes(inputFeatures);
		ArrayList outputValues = this.getOutputValues(outputVals);
		
		FeatureNode[][] array = new FeatureNode[nodes.size()][];
		int trainCount = nodes.size() * trainingPercentage/100;
		for (int i = 0; i< trainCount; i++) {
		    ArrayList<FeatureNode> row = (ArrayList<FeatureNode>) nodes.get(i);
		    array[i] = row.toArray(new FeatureNode[row.size()]);
		}
		
		double y[] = new double[outputValues.size()];
		for (int i=0;i<y.length;i++){
			y[i] = Double.parseDouble(""+outputValues.get(i));
		}
		
		
		problem.x = array;
		problem.y = y;
		problem.l = trainCount;
		
		
		SVMParams params = new SVMParams();
		//params.kernel_type = SVMParams.RBF;
		params.C = 8.0;
		params.gamma = 0.001953125;
		
		SVMModel model = engine.svm_train(problem, params);
		
		
		double accuracy = 100;
		int testCount = (nodes.size()-trainCount);
		double[] yTestPredicted = new double[testCount];
		double[] yTestActual = new double[testCount];
		int tempCounter = 0;
		int totalPositiveActual = 0;
		int totalPositivePredicted = 0;
		int falsePositive = 0;
		int trueNegative = 0;
		int correctResult = 0;
		
		for (int i=trainCount;i<nodes.size();i++){
			ArrayList<FeatureNode> row = (ArrayList<FeatureNode>) nodes.get(i);
			double yTest = engine.svm_predict(model, row.toArray(new FeatureNode[row.size()]));
			System.out.println("===============================>>>>> Actual Value :"+y[i]+"======================= Predicted Value :"+yTest);
			yTestPredicted[tempCounter] = yTest;
			yTestActual[tempCounter] = y[i];
			
			if (y[i] == 2.0 && yTest != 2.0){
				trueNegative ++;
			}
			
			if (y[i] != 2.0 && yTest == 2.0){
				falsePositive ++;
			}
			
			if (y[i] == 2.0 && yTest == 2.0){
				correctResult ++;
			}
			
			if (y[i] != 1.0){
				totalPositiveActual ++;
			}
			
			if (yTest != 1.0){
				totalPositivePredicted ++;
			}
			if (y[i] != yTest)
			{
				accuracy = accuracy - ((double)100.0 / testCount);			
			}
		    
		}
		System.out.println("Result ==========================================>>>>>>> for :"+testCount);
		System.out.println("Accurately Predicted count :"+correctResult+"============ True Negative :"+ trueNegative+" ===== False Positive :"+falsePositive);
		System.out.println("Total Actual Positive values :"+ totalPositiveActual +" ===== Total Predicted Positive :"+totalPositivePredicted);
		System.out.println("Accurracy :"+accuracy +" %");
		
		return model;
	}

	@Override
	public void saveModelToFile(SVMModel model) {
		// TODO Auto-generated method stub

		try {
			engine.svm_save_model("dc.model", model);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			if (!values[j].trim().equals("")) {
				String[] ele = values[j].trim().split(":");
				node.index = Integer.parseInt(ele[0]);
				node.value = Integer.parseInt(ele[1]);

				featureNode.add(node);
			}
		}
		return featureNode;
		
	}
	
	private ArrayList getOutputValues (ArrayList<String> data){
		ArrayList output = new ArrayList();
		for (int i=0;i<data.size();i++){
			output.add(data.get(i));
		}
		return output;
	}

}
