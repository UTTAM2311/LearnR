package com.imaginea.dc.service;

import java.util.List;

import com.imaginea.dc.beans.NewsArticle;

public interface NewsReaderService {

	List<NewsArticle> fetchNewsFromRSSFeeds();

	
	
	
}
