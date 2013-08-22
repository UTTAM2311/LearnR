package com.imaginea.dc.mahout.model.buider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.mahout.utils.HadoopSequenceFileGenerator;
import com.imaginea.dc.service.NewsArticleService;

public class TableInputBinaryNBClassifierModelBuilder extends TsvInputClassifierModelBuilder{

	protected NewsArticleService service;
	
	public TableInputBinaryNBClassifierModelBuilder(NewsArticleService service, String localInputSeqFileName, String localVectorOutputFileDir,
			String localModelOutputFileDir, String luceneAnalyser) {		
		super(null, localInputSeqFileName, localVectorOutputFileDir, localModelOutputFileDir, luceneAnalyser);
		this.service = service;
	}

	@Override
	public void generateSequenceFile() throws ModelBuilderException {
		try {
			HadoopSequenceFileGenerator sequenceFileGenerator = new HadoopSequenceFileGenerator(localInputSeqFileName);
			
			List<NewsArticle> attricles = service.fetchArticlesForTraining();
			
			if(attricles==null) {
				attricles = new ArrayList<NewsArticle>();
			}
			for(NewsArticle atricle : attricles) {
				String catogery = "false";
				if(atricle.getIsPositive() != null && atricle.getIsPositive()) {
					 catogery = "true";
				}
				sequenceFileGenerator.addData("/" + catogery + "/" + UUID.randomUUID(), atricle.getContent());
			}
			sequenceFileGenerator.finalise();
			System.out.println("Completed writing " + attricles.size()+ " atricles.");
		} catch (FileNotFoundException e) {
			throw new ModelBuilderException("Could not find input file", e);
		} catch (IOException e) {
			throw new ModelBuilderException("Error reading the input file", e);
		}
	}

}
