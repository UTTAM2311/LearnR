package com.imaginea.dc.mahout.model.buider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.classifier.naivebayes.training.TrainNaiveBayesJob;
import org.apache.mahout.vectorizer.SparseVectorsFromSequenceFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imaginea.dc.mahout.utils.HadoopSequenceFileGenerator;
import com.imaginea.dc.service.impl.NewsArticleServiceImpl;

public class TsvInputClassifierModelBuilder extends AbstactMahoutClassifierModelBuilder {

	protected String inputTsvFile;
	
	protected String localInputSeqFileName;
	
	protected String localVectorOutputFileDir;
	
	protected String localModelOutputFileDir;
	
	protected String luceneAnalyser;
	
	private final Logger LOGGER = LoggerFactory.getLogger(NewsArticleServiceImpl.class);
	
	public TsvInputClassifierModelBuilder(String inputTsvFile,
			String localInputSeqFileName, String localVectorOutputFileDir, String localModelOutputFileDir, String luceneAnalyser) {
		this.inputTsvFile = inputTsvFile;
		this.localInputSeqFileName = localInputSeqFileName;
		this.localVectorOutputFileDir = localVectorOutputFileDir;
		this.localModelOutputFileDir = localModelOutputFileDir;
		this.luceneAnalyser =luceneAnalyser;
	}
	
	public void generateSequenceFile() throws ModelBuilderException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputTsvFile));
			HadoopSequenceFileGenerator sequenceFileGenerator = new HadoopSequenceFileGenerator(localInputSeqFileName);
			int count = 1;
			while(true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				String[] tokens = line.split("\t", 2);
				if (tokens.length != 2) {
					LOGGER.info("Skipping line: " + line);
					continue;
				}
				String category = tokens[0];
				String message = tokens[1];
				
				sequenceFileGenerator.addData("/" + category + "/" + UUID.randomUUID(), message);
				count++;
			}
			reader.close();
			sequenceFileGenerator.finalise();
			LOGGER.info("Completed writing " + count + " lines.");
		} catch (FileNotFoundException e) {
			throw new ModelBuilderException("Could not find input file", e);
		} catch (IOException e) {
			throw new ModelBuilderException("Error reading the input file", e);
		}
	}

	

	public void generateSequenceToSparseFile() throws ModelBuilderException {
		 try {
			String[] args = new String[]{"-i",localInputSeqFileName, "-o", localVectorOutputFileDir, "-a", luceneAnalyser , "-nr" ,"5", "-chunk", "1"};
			ToolRunner.run(new SparseVectorsFromSequenceFiles(), args);
		} catch (Exception e) {
			throw new ModelBuilderException("Error converting sequence to sparse vector", e);
		}
		
	}

	public void genarateModelFile() throws ModelBuilderException {
		try {
		String[] args = new String[]{"-i",localVectorOutputFileDir+"/tfidf-vectors", "-el", "-li", localModelOutputFileDir+"/label-index", "-o", localModelOutputFileDir, "-ow", "-c"};
		ToolRunner.run(new Configuration(), new TrainNaiveBayesJob(), args);
		} catch (Exception e) {
			throw new ModelBuilderException("Error building model", e);
		}
	}
	
	

}
