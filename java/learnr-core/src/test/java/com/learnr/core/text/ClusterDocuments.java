package com.learnr.core.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.core.classification.GeneratingMatrixfromMaps;
import com.learnr.core.clustering.ClustersGenerator;

public class ClusterDocuments {

	private static final Logger logger = LoggerFactory.getLogger(LemmatizerTest.class);
	
	
	// Utility Classes used
	private Lemmatizer lemmatizer;
	private StopWordUtil stopWordUtil;
	private ClustersGenerator clusterGenerator;
	
	private final String PATENT_DESC1 = "Patent based on Agriculture and farming related stuffs.";
	private final String PATENT_DESC2 = "Patent based on Gaming technologies.";
	private final String PATENT_DESC3 = "Patent based on Games";
	private final String PATENT_DESC4 = "Patent based on Electronics";
	private final String PATENT_DESC5 = "Patent based on Farming and Agriculture related stuffs.";
	
	@Before
	public void init() {
		lemmatizer = new Lemmatizer();
		stopWordUtil = new StopWordUtil();
		
	}

	@After
	public void destroy() {

	}

	
	@Test
	public void clusterDocs() {
		
		// Cluster process goes in this flow
		
		// Do the Lemmatization for all the documents
		
		List<String> patent1 = lemmatizer.lemmatize(PATENT_DESC1);
		List<String> patent2 = lemmatizer.lemmatize(PATENT_DESC2);
		List<String> patent3 = lemmatizer.lemmatize(PATENT_DESC3);
		List<String> patent4 = lemmatizer.lemmatize(PATENT_DESC4);
		List<String> patent5 = lemmatizer.lemmatize(PATENT_DESC5);
		
		
		// Remove the stop words on the lemmatized words
		
		// Convert the Strings to List of Maps -- Change it to meaningful names
		
		Map<String, Integer> patentMap1 = stopWordUtil.getWordCount(patent1);
		Map<String, Integer> patentMap2 = stopWordUtil.getWordCount(patent2);
		Map<String, Integer> patentMap3 = stopWordUtil.getWordCount(patent3);
		Map<String, Integer> patentMap4 = stopWordUtil.getWordCount(patent4);
		Map<String, Integer> patentMap5 = stopWordUtil.getWordCount(patent5);
		
		// Cumulate them as List
		
		//List<Map<String, Integer>> patentCorpus = new ArrayList<>();
		List<Map<String, Integer>> patentCorpus = new ArrayList<>();
		patentCorpus.add(patentMap1);
		patentCorpus.add(patentMap2);
		patentCorpus.add(patentMap3);
		patentCorpus.add(patentMap4);
		patentCorpus.add(patentMap5);
		
		// Generate List of all the stop words
		List<String> stopWordsGenerated = stopWordUtil.StopWords(patentCorpus);
		
		List<Map<String, Integer>> termVectorList = stopWordUtil.dimensionVector(patentCorpus);
		
		RealMatrix realMatrix = GeneratingMatrixfromMaps.MapToMatrix(termVectorList);
		
		clusterGenerator = new ClustersGenerator(realMatrix);
		
		List<CentroidCluster<Clusterable>> clusters = clusterGenerator.clusterUsingKMeansPlusPlus(3);		
		System.out.println("Done ... ");
		
	}


	
}
