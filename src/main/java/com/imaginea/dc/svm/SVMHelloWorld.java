package com.imaginea.dc.svm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


import libsvm.model.SVMEngine;
import libsvm.svm.model.FeatureNode;
import libsvm.svm.model.SVMModel;
import libsvm.svm.model.SVMParams;
import libsvm.svm.model.SVNProblem;

public class SVMHelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		SVNProblem problem = new SVNProblem();
		
		ArrayList<ArrayList<FeatureNode>> nodes = new ArrayList<ArrayList<FeatureNode>>();
		
		
		int index = 1;
		for (int i = 0; i < 20000; i++) {
			ArrayList<FeatureNode> featureNode = new ArrayList<FeatureNode>();
			for (int j = 0; j < 20000; j++) {
				FeatureNode node = new FeatureNode();
				node.index = index ++;
				node.value = 1.0;
				featureNode.add(node);
			}
			nodes.add(featureNode);
		}
		
		double[] y = new double[20000];
		for (int i=0;i<1000;i++){
			y[i] = 1.0;
			y[i+5] = -1.0;
		}
		
		FeatureNode[][] array = new FeatureNode[nodes.size()][];
		for (int i = 0; i < nodes.size(); i++) {
		    ArrayList<FeatureNode> row = nodes.get(i);
		    array[i] = row.toArray(new FeatureNode[row.size()]);
		}
		
		
		
		
		problem.x = array;
		problem.y = y;
		problem.l = 20000;
		
		SVMEngine engine = new SVMEngine();
		SVMParams params = new SVMParams();
		
		SVMModel model = engine.svm_train(problem, params);
		
		engine.svm_save_model("test.model", model);
		
		FeatureNode[] xTest = new FeatureNode[20000];
		
		for (int i=0;i<20000;i++){
			FeatureNode nodeTest = new FeatureNode();
			nodeTest.index = i;
			nodeTest.value = 1.0;
			
			xTest[i] = nodeTest;
		}
		double yTest = engine.svm_predict(model, xTest);
		System.out.println("Predicted Output value :"+yTest);
	}

}
