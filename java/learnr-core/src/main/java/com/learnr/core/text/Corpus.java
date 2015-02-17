package com.learnr.core.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.linear.RealMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.util.Verify;

/**
 * In linguistics, a corpus or text corpus is a large and structured set of texts. As the definition says, a corpus
 * holds a set of texts and can be accessed as {@link TermFreqVector}s. These text vectors are used for analysis of the
 * text corpus.
 * 
 * <p>
 * Make sure to look at {@link #process()} method.
 * </p>
 * 
 * @param <I>
 *            is the Identifier type with which the vectors are identified uniquely.
 * @see TermFreqVector
 */
public class Corpus<I> {

	private static final Logger logger = LoggerFactory.getLogger(Corpus.class);

	private final Map<I, String> corpus;
	private final int size;

	private List<TermFreqVector<I>> termVectors = new ArrayList<TermFreqVector<I>>();

	private Set<String> vocabulary = new HashSet<String>();
	private Set<String> corpusStopWords = new HashSet<String>();

	private List<String> dimensionVector = new ArrayList<String>();

	/* --- Constructors --- */

	/**
	 * Constructs the Corpus with a bunch of texts uniquely identified for information retrieval.
	 * 
	 * @param inCorpus
	 *            Map of identifiers to their corresponding texts
	 */
	public Corpus(Map<I, String> inCorpus) {
		Verify.notEmpty(inCorpus);

		this.corpus = inCorpus;
		this.size = inCorpus.size();

		logger.info("Text corpus size : " + size);
	}

	/* --- Methods --- */

	/**
	 * The main (kind of) method that does all the required text processing on the current corpus. Here is the detailed
	 * list of actions that are applied on the data set in the same order.
	 * 
	 * <ul>
	 * <li>Generate {@link TermFreqVector} for each entry.</li>
	 * <li>Calculate the Vocabulary of the corpus.</li>
	 * <li>Find the Stop-words in the corpus.</li>
	 * <li>Generate the Dimension vector of corpus using Vocabulary and Stop-words.</li>
	 * <li>Finally update the {@link TermFreqVector}s of the corpus with the dimension vector.</li>
	 * </ul>
	 */
	public void process() {

		String origText;
		TermFreqVector<I> tfVector;
		Map<String, Integer> tf;

		logger.info("Generating term frequency vectors ... ");
		for (I i : corpus.keySet()) {
			origText = corpus.get(i);
			tfVector = new TermFreqVector<I>(i, origText);

			// process text frequency and update vocabulary
			tf = tfVector.getTermFrequency();
			vocabulary.addAll(tf.keySet());

			// Add to the list
			termVectors.add(tfVector);
		}

		// Note : dimension vector = vocabulary - ( standard stopwords + corpus stopwords )

		// Corpus stop words
		corpusStopWords = StopWords.findStopWordsFromTermVectors(termVectors);
		logger.info("Corpus specific stop words : " + corpusStopWords);

		// whole stop word set
		Set<String> stopWords = new HashSet<String>();
		stopWords.addAll(corpusStopWords);
		stopWords.addAll(StopWords.STANDARD_STOPWORDS);

		// Final dimension vector
		dimensionVector.addAll(vocabulary);
		dimensionVector.removeAll(stopWords);

		// logger.info("Corpus dimension vector : " + dimensionVector);
		logger.info("Corpus dimension vector length : " + dimensionVector.size());
		logger.info("Updating the term frequency vectors with the corpus dimension vector");

		// prepare term Freq vectors with dimension vector
		for (TermFreqVector<I> tfv : termVectors) {
			tfv.updateTermDimensionVector(dimensionVector);
		}

	}

	/* --- Getters and Setters --- */

	/**
	 * Gets the total count of text entries in the corpus.
	 * 
	 * @return size of the corpus.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets the entries of corpus as term frequency vectors.
	 * 
	 * @return a list of {@link TermFreqVector}s.
	 */
	public List<TermFreqVector<I>> getTermVectors() {
		return Collections.unmodifiableList(termVectors);
	}

	/**
	 * Gets the Vocabulary (all distinct words) present in the corpus.
	 * 
	 * @return set of all distinct words.
	 */
	public Set<String> getVocabulary() {
		return Collections.unmodifiableSet(vocabulary);
	}

	/**
	 * Gets the corpus specific stop-words.
	 * 
	 * @return set of stop-words.
	 */
	public Set<String> getCorpusStopWords() {
		return Collections.unmodifiableSet(corpusStopWords);
	}

	/**
	 * Gets the dimension vector of the corpus.
	 * 
	 * @return list of dimensions(words).
	 */
	public List<String> getDimensionVector() {
		return Collections.unmodifiableList(dimensionVector);
	}
	
	
	public RealMatrix getTFIDFasMatrix() {
		
		// TODO complete
		return null;
	}

}
