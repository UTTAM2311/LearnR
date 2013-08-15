package com.imaginea.dc.newsreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.imaginea.dc.api.NewsReader;
import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.utils.TextProcessingUtil;

public class NyTimesNewsReader implements NewsReader {

	public List<NewsArticle> processRSSfeed(String reeFeedUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	public String crawlUrlForContent(String articleUrl) throws IOException {
		
		URL pageUrl = new URL(articleUrl);
		
		URLConnection uconn = pageUrl.openConnection();
		HttpURLConnection conn = (HttpURLConnection) uconn;
		BufferedReader pageHtml = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		boolean slicing = false;

		String htmlLine = "";
		String postHtml = "";
		while ((htmlLine = pageHtml.readLine()) != null) {
			htmlLine.trim();
			if (!htmlLine.isEmpty()) {
				if (htmlLine.contains("<div id=\"articleBody\">")) {
					slicing = true;
					postHtml = "";
				}
				
				if (htmlLine.endsWith("<!--story end -->")) {
					slicing = false;
				}
				
				if (slicing) {
					postHtml += htmlLine;
				}
			}
		}
		
		// HTML Stripping
		postHtml = TextProcessingUtil.stripHtml(postHtml);
		
		return postHtml;
	}

}
