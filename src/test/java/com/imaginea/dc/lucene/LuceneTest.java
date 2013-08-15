package com.imaginea.dc.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.junit.Test;

import com.imaginea.dc.api.NewsReader;
import com.imaginea.dc.newsreaders.BBCNewsReader;

public class LuceneTest {

	private NewsReader bbcNewsReader = new BBCNewsReader();
	
	@Test
	public void testLuceneAnalyzer() throws Exception {
		
		String Url = "http://www.bbc.co.uk/news/world-latin-america-23166213";
		String content = bbcNewsReader.crawlUrlForContent(Url);
		
		System.out.println(content);
		
	}
	
}
