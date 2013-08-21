package com.imaginea.dc.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.imaginea.dc.entities.NewsArticle;

public interface NewsProvider {
	
	/* RSS Feeds */
	
	List<NewsArticle> processRSSfeed(String rssFeedUrl) throws IOException;
	
	
	/* Web Crawling for individual Article content */
	
	NewsArticle crawlUrlForNewsArticle(String articleUrl) throws IOException;
	
	
	/* Archive processing */
	
	String prepareArchiveUrlByDate(Date date);
	
	List<NewsArticle> fetchNewsArticlesByDate(Date date) throws IOException;
	

}
