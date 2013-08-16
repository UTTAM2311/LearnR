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
	
	private SVMEngine engine = new SVMEngine();
	
	public void train() throws Exception{
	
	SVNProblem problem = new SVNProblem();
	
	ArrayList<String> inputFeatures = dataPreprocessingService.getSVMInputData();
	ArrayList<String> ouputValues = dataPreprocessingService.getOutputValues();
	
	ArrayList nodes = this.getFeatureNodes(inputFeatures);
	ArrayList outputValues = this.getOutputValues(ouputValues);
	
	FeatureNode[][] array = new FeatureNode[nodes.size()][];
	for (int i = 0; i < nodes.size(); i++) {
	    ArrayList<FeatureNode> row = (ArrayList<FeatureNode>) nodes.get(i);
	    array[i] = row.toArray(new FeatureNode[row.size()]);
	}
	
	double y[] = new double[outputValues.size()];
	for (int i=0;i<y.length;i++){
		y[i] = Double.parseDouble(""+outputValues.get(i));
	}
	
	
	problem.x = array;
	problem.y = y;
	problem.l = nodes.size();
	
	
	SVMParams params = new SVMParams();
	
	model = engine.svm_train(problem, params);
	
	engine.svm_save_model("test.model", model);
	
	/*FeatureNode[] xTest = new FeatureNode[20000];
	
	for (int i=0;i<20000;i++){
		FeatureNode nodeTest = new FeatureNode();
		nodeTest.index = i;
		nodeTest.value = 1.0;
		
		xTest[i] = nodeTest;
	}
	double yTest = engine.svm_predict(model, xTest);
	System.out.println("Predicted Output value :"+yTest);*/
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
