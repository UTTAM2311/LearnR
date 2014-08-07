package com.learnr.core.text;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.util.Verify;

public class TextProcessor {

	private static final Logger logger = LoggerFactory.getLogger(TextProcessor.class);

	private final Map<String, String> corpus;
	private final int corpusSize;
	
	private List<String> dimensionVector;

	public TextProcessor(Map<String, String> inCorpus) {
		Verify.notNull(inCorpus);

		this.corpus = inCorpus;
		this.corpusSize = inCorpus.size();
		
		logger.info("Text corpus size : " + corpusSize);
	}

	/* --- Methods --- */
	
	public void process() {
		// Do all the non-sense
	}
	
	// Lemmatize corpus
	
	// getWordcount of corpus
	
	
	// TODO Get StopWords
	
	public List<TermFreVector> getTermFrequencyVectors() {
		return (List<TermFreVector>) getTermFrequencyVectorsAsMap().values();
	}
	
	public Map<String, TermFreVector> getTermFrequencyVectorsAsMap() {
		
		// TODO get TermVectors
		
		return null;
	}
	
	// TODO get TermVectorMap
	
	
	
	/* --- Getters and Setters --- */
	
	public Map<String, String> getCorpus() {
		return Collections.unmodifiableMap(corpus);
	}

	public Integer getCorpusSize() {
		return corpusSize;
	}

	public List<String> getDimensionVector() {
		return Collections.unmodifiableList(dimensionVector);
	}

}
