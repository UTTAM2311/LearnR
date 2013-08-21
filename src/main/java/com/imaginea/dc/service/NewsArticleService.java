package com.imaginea.dc.service;

import java.util.List;

import com.imaginea.dc.entities.NewsArticle;

public interface NewsArticleService {
	
	/* NewsArticle CRUD */
	
	void createNewsArticle(NewsArticle article);
	
	NewsArticle readNewsArticle(Integer pkey);
	
	void updateNewsArticle(NewsArticle article);
	
	void deleteNewsArticle(Integer pkey);
	
	
	List<NewsArticle> readNewsArticle(String title);
	
	
	/* News Articles paginated */
	
	List<NewsArticle> fetchAllArticles(Integer pageNumber, Integer pageSize);
	
	List<NewsArticle> fetchArticlesBySource(String source, Integer pageNumber, Integer pageSize);
	
	
	List<NewsArticle> fetchAllUnlabelledArticles(Integer pageNumber, Integer pageSize);
	

	List<NewsArticle> fetchArticlesForTraining();
	
	List<NewsArticle> fetchArticlesForTraining(Integer pageNumber, Integer pageSize);
	

}

