package com.imaginea.dc.newsreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.imaginea.dc.api.NewsReader;
import com.imaginea.dc.constants.NewsSource;
import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.utils.TextProcessingUtil;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class BBCNewsReader implements NewsReader {
	
	private final String bbcRssFeedUrl = "http://feeds.bbci.co.uk/news/world/rss.xml";

	public List<NewsArticle> processRSSfeed(String rssFeedUrl) throws IOException {
		if(rssFeedUrl == null || rssFeedUrl.isEmpty()) {
			rssFeedUrl = this.bbcRssFeedUrl;
		}
		
		System.out.println("BBC RSS Feed URL : " + rssFeedUrl);
		
		List<NewsArticle> articles = new ArrayList<NewsArticle>();
		XmlReader reader = null;
		
		try {
			URL url = new URL(rssFeedUrl);
			reader = new XmlReader(url);
			
			SyndFeed feed = new SyndFeedInput().build(reader);
			System.out.println("Feed Title: " + feed.getAuthor());
			
			System.out.println("total feeds count : " + feed.getEntries().size());

			SyndEntry entry;
			SyndContent content;
			
			NewsArticle article;
			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				entry = (SyndEntry) i.next();
				content = entry.getDescription();
				
				article = new NewsArticle();
				
				article.setSource(NewsSource.BBC);
				article.setPublisher(NewsSource.BBC);
				article.setAuthor(entry.getAuthor());
				
				article.setPublishedDate(entry.getPublishedDate());
				article.setUpdatedDate(entry.getUpdatedDate());
				
				article.setUrl(entry.getUri());
				article.setTitle(entry.getTitle());
				article.setDescription(content.getValue());
				
				// Content
				if(article.getUrl() != null) {
					article.setContent(this.crawlUrlForContent(article.getUrl()));
				}
				
				articles.add(article);
				
				System.out.println("---------------------------------------------------------------");
				System.out.println("Feed Title : " + entry.getTitle());
				
				
			}			
			
		} catch (FeedException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		
		return articles;
	}
	
	public String crawlUrlForContent(String articleUrl) throws IOException {
		
		System.out.println("Article URL : " + articleUrl);
		
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
				if (htmlLine.contains("<div class=\"story-body\">")) {
					slicing = true;
					postHtml = "";
				}
				
				if (htmlLine.endsWith("<!-- / story-body -->")) {
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
