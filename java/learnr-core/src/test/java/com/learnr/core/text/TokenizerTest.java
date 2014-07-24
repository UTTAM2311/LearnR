package com.learnr.core.text;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenizerTest {

	private static final Logger logger = LoggerFactory.getLogger(TokenizerTest.class);

	private String I_AM_A_BOY = "I am a Boy";
	private String I_AM_RUNNING = "I am running";
	private String I_AM_RUNNING_RUNNING = "I am running running";

	
	private Tokenizer tokenizer;
	
	@Before
	public void init() {

	}

	@After
	public void destroy() {

	}

	
	@Test(expected = IllegalArgumentException.class)
	public void test_tokenize_null_argument() {
		tokenizer.tokenize(null);
	}

	@Test
	public void test_tokenize_str_with_4words() {
		List<String> tokens = tokenizer.tokenize(I_AM_A_BOY);
		Assert.assertEquals(4, tokens.size());
		Assert.assertEquals(true, tokens.contains("am"));
	}

	@Test
	public void test_tokenize_str_with_3words() {
		List<String> tokens = tokenizer.tokenize(I_AM_RUNNING);
		Assert.assertEquals(3, tokens.size());
		Assert.assertEquals(true, tokens.contains("running"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_getWordCount_null_argument() {
		Map<String, Integer> wc = tokenizer.getWordCount(null);
	}

}
