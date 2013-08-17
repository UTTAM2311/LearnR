package com.imaginea.dc.service;

import java.util.List;

import com.imaginea.dc.entities.NewsArticle;

public interface NewsReaderService {
	
	/* NewsArticle CRUD */
	
	void createNewsArticle(NewsArticle article);
	
	NewsArticle readNewsArticle(Integer pkey);
	
	void updateNewsArticle(NewsArticle article);
	
	void deleteNewsArticle(Integer pkey);
	
	
	List<NewsArticle> readNewsArticle(String title);
	
	List<NewsArticle> fetchAllArticles();
	
	
	List<NewsArticle> fetchAllUnlabelledArticles();
	
	List<NewsArticle> fetchArticlesForTraining();
	

}

