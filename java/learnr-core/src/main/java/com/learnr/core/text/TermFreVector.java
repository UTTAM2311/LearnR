package com.learnr.core.text;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.linear.ArrayRealVector;

public class TermFreVector {

	private String id;

	private String originalText;

	private int wordCount;
	private int distinctWordCount;

	private Map<String, Integer> frequency;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public int getDistinctWordCount() {
		return distinctWordCount;
	}

	public void setDistinctWordCount(int distinctWordCount) {
		this.distinctWordCount = distinctWordCount;
	}

	public Map<String, Integer> getFrequency() {
		return frequency;
	}

	public void setFrequency(Map<String, Integer> frequency) {
		this.frequency = frequency;
	}

	
	public ArrayRealVector getAsVector(List<String> dimensionVector) {
		return null;
	}
	
	
}
