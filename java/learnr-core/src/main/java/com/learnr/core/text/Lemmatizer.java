package com.learnr.core.text;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.util.Verify;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

/**
 * A text lemmatization utility.
 */
public class Lemmatizer {

	private static final Logger logger = LoggerFactory.getLogger(Lemmatizer.class);

	private static final StanfordCoreNLP pipeline;

	static {
		Properties props;
		props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma");

		// StanfordCoreNLP loads a lot of models, so you probably
		// only want to do this once per execution
		pipeline = new StanfordCoreNLP(props);
	}

	public static List<String> lemmatize(String inStr) {
		Verify.hasLength(inStr);
		logger.debug("Incoming String for lemmatization : " + inStr);

		List<String> lemmas = new LinkedList<String>();

		// create an empty Annotation just with the given text
		Annotation document = new Annotation(inStr);

		// run all Annotators on this text
		pipeline.annotate(document);

		// Iterate over all of the sentences found
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			// Iterate over all tokens in a sentence
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {

				// Retrieve and add the lemma for each word into the list of lemmas
				lemmas.add(token.get(LemmaAnnotation.class));
			}
		}

		return lemmas;
	}

	/**
	 * Returns a set of lemmas present in the passed text.
	 * 
	 * @param inText
	 *            the original text as string
	 * @return a set of lemmas
	 */
	public static Set<String> getDistinctLemmas(String inText) {
		if (inText == null || inText.isEmpty())
			return null;

		// convert to lowercase
		String text = inText.toLowerCase();

		// Get tokens (lemmatized)
		List<String> tokens = Lemmatizer.lemmatize(text);
		if (tokens != null)
			return new HashSet<String>(tokens);

		return null;
	}

	/**
	 * Returns a set of lemmas present in the passed collections of texts.
	 * 
	 * @param inTextList
	 *            a set of texts (corpus)
	 * @return the union of lemmas of each text entry
	 */
	public static Set<String> getDistinctLemmas(Collection<String> inTextList) {
		if (inTextList == null)
			return null;

		Set<String> distinctWords = new HashSet<String>();
		for (String inText : inTextList) {
			distinctWords.addAll(getDistinctLemmas(inText));
		}

		return distinctWords;
	}

}
