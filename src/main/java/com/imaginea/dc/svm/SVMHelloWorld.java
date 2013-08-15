package com.imaginea.dc.svm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
		
		FeatureNode[][] nodes = new FeatureNode[20000][20000];
		
		
		int index = 1;
		for (int i = 0; i < 20000; i++) {
			for (int j = 0; j < 20000; j++) {
				FeatureNode node = new FeatureNode();
				node.index = index ++;
				node.value = 1.0;
				nodes[i][j] = node;
			}
		}
		
		double[] y = new double[20000];
		for (int i=0;i<1000;i++){
			y[i] = 1.0;
			y[i+5] = -1.0;
		}
		
		problem.x = nodes;
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
