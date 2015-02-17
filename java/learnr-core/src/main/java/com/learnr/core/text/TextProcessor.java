package com.learnr.core.text;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple text processing utility.
 */
public class TextProcessor {

	private static final Logger logger = LoggerFactory.getLogger(TextProcessor.class);

	/**
	 * Given a text calculates the term frequencies of the same. To calculate the same, the passed string in tokenized
	 * and lemmatized in order to avoid redundancies.
	 * 
	 * @param inText
	 *            the original text as string
	 * @return A Map of terms/words present in the document to their frequencies.
	 * 
	 * @see Lemmatizer
	 */
	public static Map<String, Integer> getTermFrequency(String inText) {
		if (inText == null || inText.isEmpty())
			return null;

		Map<String, Integer> tfMap = new HashMap<String, Integer>();

		// convert to lowercase
		String text = inText.toLowerCase();

		// Get tokens (lemmatized)
		List<String> tokens = Lemmatizer.lemmatize(text);
		logger.debug("Lemmatized tokens : " + tokens);

		for (String term : tokens) {
			int count = 1;
			if (tfMap.containsKey(term))
				count = tfMap.get(term) + 1;

			tfMap.put(term, count);
		}

		return tfMap;
	}

}
