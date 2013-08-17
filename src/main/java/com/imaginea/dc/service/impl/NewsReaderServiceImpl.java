package com.imaginea.dc.service.impl;

import java.util.Hashtable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.imaginea.dc.api.IDao;
import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.service.NewsReaderService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class NewsReaderServiceImpl implements NewsReaderService {
	
	private IDao genericDao;
	
	
	/* Implementations */
	
	@Transactional
	public void createNewsArticle(NewsArticle article) {
		if(article != null && article.getTitle() != null) {
			// Look for existing articles  
			List<NewsArticle> tempArticles = this.readNewsArticle(article.getTitle());
			if(tempArticles == null || tempArticles.size() == 0) {
				// Save on new
				try {
				genericDao.save(article);
				} catch(Exception e) {
					if(e instanceof MySQLIntegrityConstraintViolationException) {
						System.out.println(e.getMessage());
					}
				}
			} else {
				// TODO : Check for update
				if(article.getUpdatedDate() != null) {
					final NewsArticle tempArticle = tempArticles.get(0);
					article.setPkey(tempArticle.getPkey());
					article.setVersion(tempArticle.getVersion());
					
					genericDao.update(article);
				}
			}
		}
	}


	public NewsArticle readNewsArticle(Integer pkey) {
		return genericDao.find(NewsArticle.class, pkey);
	}

	@Transactional
	public void updateNewsArticle(NewsArticle article) {
		if(article != null && article.getPkey() != null) {
			genericDao.update(article);
		}
	}

	@Transactional
	public void deleteNewsArticle(Integer pkey) {
		NewsArticle article = this.readNewsArticle(pkey);
		if(article != null) {
			genericDao.delete(article);
		}
	}
	
	
	public List<NewsArticle> readNewsArticle(String title) {
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		criteria.put("title", title);
		return genericDao.getEntities(NewsArticle.class, "newsArticle.fetchByTitle", criteria);
	}
	
	
	public List<NewsArticle> fetchAllArticles() {
		return genericDao.findAllEntities(NewsArticle.class);
	}
	
	public List<NewsArticle> fetchArticlesBySource(String source){
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		criteria.put("source", source);
		return genericDao.getEntities(NewsArticle.class, "newsArticle.fetchBySource", criteria);
	}

	
	public List<NewsArticle> fetchAllUnlabelledArticles() {
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		return genericDao.getEntities(NewsArticle.class, "newsArticle.fetchAllUnblabelled", criteria);
	}
	
	public List<NewsArticle> fetchArticlesForTraining() {
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		return genericDao.getEntities(NewsArticle.class, "newsArticle.fetchLabelled", criteria);
	}
	
	
	
	/* Getters and Setters */

	public void setGenericDao(IDao genericDao) {
		this.genericDao = genericDao;
	}



}
