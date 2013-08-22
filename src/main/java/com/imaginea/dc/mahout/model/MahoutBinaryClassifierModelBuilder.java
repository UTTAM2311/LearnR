package com.imaginea.dc.mahout.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imaginea.dc.mahout.model.buider.MahoutClassifierModelBuilder;
import com.imaginea.dc.mahout.model.buider.ModelBuilderException;
import com.imaginea.dc.mahout.model.buider.TableInputBinaryNBClassifierModelBuilder;
import com.imaginea.dc.service.NewsArticleService;
import com.imaginea.dc.service.impl.NewsArticleServiceImpl;

public class MahoutBinaryClassifierModelBuilder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsArticleServiceImpl.class);

	private NewsArticleService newsArticleService;
	
	public void buildInputBinaryClassifierModel() {
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
			
			MahoutClassifierModelBuilder template = new TableInputBinaryNBClassifierModelBuilder(newsArticleService, 
					localInputSeqFileName, localVectorOutputFileDir, localModelOutputFileDir, luceneAnalyser);
			
			template.buildModel();

		} catch (IOException ex) {
			LOGGER.error("Error building the model", ex);
		} catch (ModelBuilderException e) {
			LOGGER.error("Error building the model", e);
		}

	}

	public NewsArticleService getNewsArticleService() {
		return newsArticleService;
	}

	public void setNewsArticleService(NewsArticleService newsArticleService) {
		this.newsArticleService = newsArticleService;
	}
}
