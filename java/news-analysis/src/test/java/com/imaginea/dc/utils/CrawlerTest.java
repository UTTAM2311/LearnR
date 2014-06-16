package com.imaginea.dc.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class CrawlerTest {

	
	@Test
	public void crawlForArticlesUrlsTest() throws Exception {
		
		String pageUrlStr = "http://www.reuters.com/news/archive/worldNews?date=08012013";
		
		URL pageUrl = new URL(pageUrlStr);
		
		URLConnection uconn = pageUrl.openConnection();
		HttpURLConnection conn = (HttpURLConnection) uconn;
		BufferedReader pageHtml = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		
		// Extract days

		String htmlLine = "";
		String postHtml = "";
		
		System.out.println(postHtml);
		
		
	}
	
	
	@Test
	public void crawlForContentTest() throws Exception {
		
		String pageUrlStr = "http://www.reuters.com/article/2013/08/18/us-zimbabwe-election-idUSBRE97H09P20130818?feedType=RSS&feedName=worldNews";
		
		URL pageUrl = new URL(pageUrlStr);
		
		URLConnection uconn = pageUrl.openConnection();
		HttpURLConnection conn = (HttpURLConnection) uconn;
		BufferedReader pageHtml = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		// Extract days
		boolean slicing = false;

		String htmlLine = "";
		String postHtml = "";
		while ((htmlLine = pageHtml.readLine()) != null) {
			htmlLine.trim();
			if (!htmlLine.isEmpty()) {
				if (htmlLine.contains("id=\"articleText\">")) {
					slicing = true;
					postHtml = "";
				}
				
				if (htmlLine.endsWith("<div class=\"relatedTopicButtons\">")) {
					slicing = false;
				}
				
				if (slicing) {
					postHtml += htmlLine;
				}
			}
		}
		
		postHtml = TextProcessingUtil.stripHtml(postHtml);
		
		System.out.println(postHtml);
		
		
	}
	
}
