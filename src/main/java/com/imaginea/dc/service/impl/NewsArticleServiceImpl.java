package com.imaginea.dc.service.impl;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.imaginea.dc.api.IDao;
import com.imaginea.dc.entities.BaseNewsArticle;
import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.entities.SubNewsArticle;
import com.imaginea.dc.service.NewsArticleService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class NewsArticleServiceImpl implements NewsArticleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsArticleServiceImpl.class);
	
	private IDao genericDao;
	
	
	/* Implementations */
	
	@Transactional
	public void createNewsArticle(NewsArticle article) {
		if(article != null && article.getTitle() != null) {
			try {
				genericDao.save(article);
			} catch(Exception e) {
				
				LOGGER.error(e.getMessage());
				if(e instanceof MySQLIntegrityConstraintViolationException) {
					LOGGER.error("Error while saving : Duplicate entry fo the title ");
					
					NewsArticle parent = this.readNewsArticleByUniqueValue(article.getUniqueValue());
					if(parent != null) {
						SubNewsArticle subArticle = new SubNewsArticle(article);
						subArticle.setNewsArticle(parent);
						
						genericDao.save(subArticle);
					}
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
	
	
	@Transactional
	public void createSubNewsArticle(SubNewsArticle subArticle) {
		if(subArticle != null && subArticle.getNewsArticle() != null) {
			genericDao.save(subArticle);
		}
	}
	
	
	
	public NewsArticle readNewsArticleByUniqueValue(String uniqueValue) {
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		criteria.put("uniqueValue", uniqueValue);
		return genericDao.getEntity(NewsArticle.class, "newsArticle.fetchByUniqueValue", criteria);
	}
	
	public List<NewsArticle> readNewsArticle(String title) {
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		criteria.put("title", title);
		return genericDao.getEntities(NewsArticle.class, "newsArticle.fetchByTitle", criteria);
	}
	
	
	
	
	public List<NewsArticle> fetchAllArticles(Integer pageNumber, Integer pageSize) {
		return genericDao.findAllEntities(NewsArticle.class, null, pageNumber, pageSize);
	}
	
	public List<NewsArticle> fetchArticlesBySource(String source, Integer pageNumber, Integer pageSize){
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		criteria.put("source", source);
		return genericDao.getEntities(NewsArticle.class, "newsArticle.fetchBySource", criteria, pageNumber, pageSize);
	}

	
	public List<NewsArticle> fetchAllUnlabelledArticles(Integer pageNumber, Integer pageSize) {
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		return genericDao.getEntities(NewsArticle.class, "newsArticle.fetchAllUnblabelled", criteria, pageNumber, pageSize);
	}
	
	
	public List<NewsArticle> fetchArticlesForTraining() {
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		return genericDao.getEntities(NewsArticle.class, "newsArticle.fetchLabelled", criteria);
	}
	
	public List<NewsArticle> fetchArticlesForTraining(Integer pageNumber, Integer pageSize) {
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		return genericDao.getEntities(NewsArticle.class, "newsArticle.fetchLabelled", criteria, pageNumber, pageSize);
	}
	
	
	
	/* Getters and Setters */

	public void setGenericDao(IDao genericDao) {
		this.genericDao = genericDao;
	}



}
