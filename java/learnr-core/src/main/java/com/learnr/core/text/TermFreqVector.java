package com.learnr.core.text;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.ml.clustering.Clusterable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.core.exception.LearnRException;
import com.learnr.core.math.Distance;
import com.learnr.util.Verify;

/**
 * A representation of a text's term frequency vector. Every text needs a vector representation for analysis. Here we
 * are using the term frequency of the content for its vector representation.
 * 
 * @param <I>
 *            is the Unique Identifier type of this vector.
 * @see Corpus
 * @see TextProcessor
 */
public final class TermFreqVector<I> implements Clusterable {

	private static final Logger logger = LoggerFactory.getLogger(TermFreqVector.class);

	private final I id;
	private final String originalText;

	private final Map<String, Integer> termFrequency;

	private final int wordCount;
	private final int distinctWordCount;

	private double[] point = null;

	/* --- Constructors --- */

	/**
	 * Constructor with original text.
	 * 
	 * @param id
	 *            The unique identifier of this vector.
	 * @param originalText
	 *            Yup!.. its the Original text.
	 */
	public TermFreqVector(I id, String originalText) {
		super();
		Verify.notNull(id, originalText);

		this.id = id;
		this.originalText = originalText;
		this.termFrequency = TextProcessor.getTermFrequency(originalText);

		// word counts
		this.wordCount = calculateWordCount();
		this.distinctWordCount = this.termFrequency.keySet().size();
	}

	/**
	 * Constructor with text/documents term frequency as map.
	 * 
	 * @param id
	 *            The unique identifier of this vector.
	 * @param termFrequency
	 *            Yo dawg! I see you already calculated the term frequency.
	 */
	public TermFreqVector(I id, Map<String, Integer> termFrequency) {
		super();
		Verify.notNull(id, termFrequency);

		this.id = id;
		this.originalText = null;
		this.termFrequency = termFrequency;

		// word counts
		this.wordCount = calculateWordCount();
		this.distinctWordCount = this.termFrequency.keySet().size();
	}

	/* --- Private Helpers --- */

	/**
	 * Calculates the total word count of this document.
	 * which a respective word is preent
	 * 
	 * @return the word count.
	 */
	private final int calculateWordCount() {
		int wCount = 0;
		Collection<Integer> counts = termFrequency.values();
		for (Integer count : counts) {
			if (count != null)
				wCount = wCount + count;
		}

		return wCount;
	}

	/* --- Getters and Setters --- */

	/**
	 * Gets the unique identifier of this document.
	 * 
	 * @return identifier of type <I>
	 */
	public I getId() {
		return id;
	}

	/**
	 * Gets the Original text of the Vector.
	 * 
	 * @return the original text as String.
	 */
	public String getOriginalText() {
		if (originalText == null)
			throw new LearnRException("Object was initialized with no original text.");

		return originalText;
	}

	/**
	 * Gets the total word count of the text.
	 * <p>
	 * 
	 * @return the word count.
	 */
	public int getWordCount() {
		return wordCount;
	}

	/**
	 * Gets the total no of distinct words present in this text.
	 * 
	 * @return the no of distinct words.
	 */
	public int getDistinctWordCount() {
		return distinctWordCount;
	}

	/**
	 * Gets the word frequency as map of Word to its frequency.
	 * 
	 * @return the term frequency map.
	 */
	public Map<String, Integer> getTermFrequency() {
		return Collections.unmodifiableMap(termFrequency);
	}

	/* --- Other Methods --- */

	/**
	 * As every vector depends on the basis of its vector space, a basis vector (or dimension vector) is needed to get
	 * its vector form. The term frequency is used to get the corresponding vector as per the dimension vector. Once
	 * updated the generated vector can be accessed using {@link #getPoint()} method.
	 * 
	 * @param dimensionVector
	 *            the dimensions/features (simply the ordered list of words) of the {@link Corpus}.
	 * 
	 * @see #getPoint()
	 */
	public void updateTermDimensionVector(List<String> dimensionVector) {
		Verify.notEmpty(dimensionVector);

		int length = dimensionVector.size();
		point = new double[length];

		// Update the vector as per dimension vector
		String term;
		Integer freq;
		for (int i = 0; i < dimensionVector.size(); i++) {
			term = dimensionVector.get(i);
			freq = termFrequency.get(term);

			point[i] = (freq == null) ? 0 : freq;
		}

	}

	/**
	 * Calculates the Euclidean distance (L2 Norm) to the passed vector.
	 * 
	 * @param vector
	 *            between which the distance needs to be calculated.
	 * @return the distance as <code>double</code>
	 */
	public double getEuclideanDistanceFrom(TermFreqVector<I> vector) {
		double distance = Distance.euclideanDistance(this.getPoint(), vector.getPoint());
		logger.debug("Distances detween Id :" + this.id + "and Id :" + vector.getId() + " is : " + distance);
		return distance;
	}

	/* --- Clustering related --- */

	@Override
	public double[] getPoint() {
		if (this.point == null)
			throw new LearnRException("Object is not yet processed term dimension vector");

		return this.point;
	}

}
