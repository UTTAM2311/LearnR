package com.imaginea.dc.mahout.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.imaginea.dc.mahout.model.buider.MahoutClassifierModelBuilder;
import com.imaginea.dc.mahout.model.buider.ModelBuilderException;
import com.imaginea.dc.mahout.model.buider.TableInputBinaryNBClassifierModelBuilder;
import com.imaginea.dc.mahout.model.buider.TsvInputClassifierModelBuilder;
import com.imaginea.dc.service.NewsReaderService;

public class MahoutBinaryClassifierModelBuilder {

	public static NewsReaderService service;
	
	public static void main(String[] args) {
		Properties prop = new Properties();

		try {
			// load a properties file
			prop.load(new FileInputStream("\\git\\DeathCluster\\src\\main\\resources\\mahout.properties"));

			// get the property value and print it out
			//String hdfsName = prop.getProperty("hdfs.name");
			//String hdfsURI = prop.getProperty("hdfs.uri");
			//String hdfsBaseDir = prop.getProperty("hdfs.base.dir");
			
			String localBaseDir = prop.getProperty("local.base.dir");
			
			String localInputSeqFileName = localBaseDir + prop.getProperty("model1.local.input.sequence.filename");

			String localVectorOutputFileDir = localBaseDir + prop.getProperty("model1.local.output.vector.filedir");

			String localModelOutputFileDir = localBaseDir + prop.getProperty("model1.local.output.model.filedir");
			
			String luceneAnalyser = prop.getProperty("model1.lucene.analyser");
			
			MahoutClassifierModelBuilder template = new TableInputBinaryNBClassifierModelBuilder(service, 
					localInputSeqFileName, localVectorOutputFileDir, localModelOutputFileDir, luceneAnalyser);
			
			template.buildModel();

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ModelBuilderException e) {
			e.printStackTrace();
		}

	}
}
