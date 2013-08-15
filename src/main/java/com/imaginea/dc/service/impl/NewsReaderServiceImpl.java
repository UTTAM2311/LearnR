package com.imaginea.dc.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.imaginea.dc.api.IDao;
import com.imaginea.dc.api.NewsReader;
import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.newsreaders.BBCNewsReader;
import com.imaginea.dc.newsreaders.HinduNewsReader;
import com.imaginea.dc.newsreaders.NyTimesNewsReader;
import com.imaginea.dc.service.NewsReaderService;

public class NewsReaderServiceImpl implements NewsReaderService {
	
	private IDao genericDao;
	
	/* News Readers */
	private BBCNewsReader bbcNewsReader;
	private HinduNewsReader hinduNewsReader;
	private NyTimesNewsReader nyTimesNewsReader;
	
	
	/* Implementations */
	
	@Transactional
	public void createNewsArticle(NewsArticle article) {
		if(article != null) {
			genericDao.save(article);
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
	
	
	/* RSS Feeds */

	@Transactional
	public List<NewsArticle> fetchNewsFromRSSFeeds() {
		
		List<NewsArticle> articles = new ArrayList<NewsArticle>();
		
		try {
			List<NewsArticle> bbcfeeds = bbcNewsReader.processRSSfeed(null);
			if(bbcfeeds != null)
				articles.addAll(bbcfeeds);
			
			List<NewsArticle> hindufeeds = hinduNewsReader.processRSSfeed(null);
			if(hindufeeds != null)
				articles.addAll(hindufeeds);
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (NewsArticle newsArticle : articles) {
			this.createNewsArticle(newsArticle);
		}
		
		return articles;
	}

	
	/* Getters and Setters */

	public void setGenericDao(IDao genericDao) {
		this.genericDao = genericDao;
	}

	public void setBbcNewsReader(BBCNewsReader bbcNewsReader) {
		this.bbcNewsReader = bbcNewsReader;
	}

	public void setHinduNewsReader(HinduNewsReader hinduNewsReader) {
		this.hinduNewsReader = hinduNewsReader;
	}

	public void setNyTimesNewsReader(NyTimesNewsReader nyTimesNewsReader) {
		this.nyTimesNewsReader = nyTimesNewsReader;
	}



}
