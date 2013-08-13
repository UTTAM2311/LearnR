package com.dc.api;

import java.util.List;

import com.dc.beans.NewsArticle;

public interface NewsReader {
	
	/* RSS Feeds */
	
	List<NewsArticle> processRSSfeed(String reeFeedUrl);
	
	
	/* Web Crawling for individual Article content */
	
	NewsArticle crawlUrlForContent();
	
	
	/* Archive processing */
	
	
	
}
