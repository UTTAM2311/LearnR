package com.imaginea.dc.svm;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.imaginea.dc.service.DataPreProcessingService;

import libsvm.model.SVMEngine;
import libsvm.svm.model.FeatureNode;
import libsvm.svm.model.SVMModel;
import libsvm.svm.model.SVMParams;
import libsvm.svm.model.SVNProblem;

public class SVMProcessor {

	@Autowired
	private DataPreProcessingService dataPreprocessingService;
	
	private SVMModel model;
	
	@Autowired
	private SVMEngine engine;
	
	private int trainingPercentage = 70;
	
	public void train() throws Exception{
	
	SVNProblem problem = new SVNProblem();
	
	ArrayList<String> inputFeatures = dataPreprocessingService.getSVMInputData();
	ArrayList<String> ouputValues = dataPreprocessingService.getOutputValues();
	
	ArrayList nodes = this.getFeatureNodes(inputFeatures);
	ArrayList outputValues = this.getOutputValues(ouputValues);
	
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
	
	model = engine.svm_train(problem, params);
	
	engine.svm_save_model("test.model", model);
	
	
	//FeatureNode[] testarray = new FeatureNode[nodes.size() - trainCount];
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
	
	public double[] predict(ArrayList<String> inputData) throws Exception{
		double[] output = new double[inputData.size()];
		Iterator<String> inputDataIterator = inputData.iterator();
		int count = 0;
		while (inputDataIterator.hasNext()){
			String inputLine = inputDataIterator.next();
			ArrayList<FeatureNode> featureNodes = this.getFeatureNodeFromLine(inputLine);
			FeatureNode[] featureNodeArray = new FeatureNode[featureNodes.size()];
			Iterator<FeatureNode> featureNodesIterator = featureNodes.iterator();
			int i =0;
			while (featureNodesIterator.hasNext()){
				featureNodeArray[i++] = featureNodesIterator.next(); 
			}
			double yTest = engine.svm_predict(model, featureNodeArray);
			output[count] = yTest;
		}		
		return output;
	}
}
