package com.learnr.core.text;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LemmatizerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LemmatizerTest.class);

	private String I_AM_A_BOY = "I am a Boy";
	private String I_AM_RUNNING = "I am running";
	private String I_AM_RUNNING_RUNNING = "I am running running";

	
	private Lemmatizer lemmatizer;
	
	@Before
	public void init() {

	}

	@After
	public void destroy() {

	}

	
	@Test(expected = IllegalArgumentException.class)
	public void test_lemmatize_null_argument() {
		lemmatizer.lemmatize(null);
	}

	@Test
	public void test_lemmatize_str_with_4words() {
		List<String> lemmas = lemmatizer.lemmatize(I_AM_A_BOY);
		Assert.assertEquals(4, lemmas.size());
		Assert.assertEquals(true, lemmas.contains("be"));
	}

	@Test
	public void test_lemmatize_str_with_3words() {
		List<String> lemmas = lemmatizer.lemmatize(I_AM_RUNNING);
		Assert.assertEquals(3, lemmas.size());
		Assert.assertEquals(true, lemmas.contains("run"));
		Assert.assertEquals(true, lemmas.contains("be"));
	}
	

}
