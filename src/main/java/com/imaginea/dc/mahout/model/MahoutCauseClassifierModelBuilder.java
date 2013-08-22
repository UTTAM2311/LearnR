package com.imaginea.dc.mahout.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imaginea.dc.mahout.model.buider.MahoutClassifierModelBuilder;
import com.imaginea.dc.mahout.model.buider.ModelBuilderException;
import com.imaginea.dc.mahout.model.buider.TableInputCauseNBClassifierModelBuilder;
import com.imaginea.dc.service.NewsArticleService;
import com.imaginea.dc.service.impl.NewsArticleServiceImpl;
import com.imaginea.dc.utils.MessageUtil;

public class MahoutCauseClassifierModelBuilder {

	private NewsArticleService newsArticleService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(NewsArticleServiceImpl.class);
	
	public void buildDeathCauseClassifierModel() {

		try {
			// get the property value and print it out
			//String hdfsName = prop.getProperty("hdfs.name");
			//String hdfsURI = prop.getProperty("hdfs.uri");
			//String hdfsBaseDir = prop.getProperty("hdfs.base.dir");
			
			String localBaseDir = MessageUtil.getMessage("local.base.dir");
			
			String localInputSeqFileName = localBaseDir + MessageUtil.getMessage("model1.local.input.sequence.filename");

			String localVectorOutputFileDir = localBaseDir + MessageUtil.getMessage("model1.local.output.vector.filedir");

			String localModelOutputFileDir = localBaseDir + MessageUtil.getMessage("model1.local.output.model.filedir");
			
			String luceneAnalyser = MessageUtil.getMessage("model1.lucene.analyser");
			
			MahoutClassifierModelBuilder template = new TableInputCauseNBClassifierModelBuilder(newsArticleService, 
					localInputSeqFileName, localVectorOutputFileDir, localModelOutputFileDir, luceneAnalyser);
			
			template.buildModel();

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
