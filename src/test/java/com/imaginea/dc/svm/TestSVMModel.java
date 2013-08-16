package com.imaginea.dc.svm;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imaginea.dc.preprocesor.PreprocessData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:appContext.xml"})
public class TestSVMModel {

	@Autowired
	private SVMProcessor svmProcessor;
	
	@Autowired
	private PreprocessData preprocessData;
	
	//@Test
	public void testModel() throws Exception{
		svmProcessor.train();
	}
	
	@Test
	public void testPredictData() throws Exception {
		ArrayList<String> testData = new ArrayList<String>();
		testData.add("This is sample death data predicted to be positive.");
		testData.add("This is sample random data predicted to be negative.");
		testData.add("This is sample random garbage data be negative.");
		testData.add("This is dead.");
		
		ArrayList<String> svmInputFormat = preprocessData.convertStringToSVMInput(testData);
		svmProcessor.train();
		svmProcessor.predict(svmInputFormat);
	}
}
