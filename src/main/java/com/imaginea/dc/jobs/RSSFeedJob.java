package com.imaginea.dc.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.newsreaders.BBCNewsReader;
import com.imaginea.dc.newsreaders.HinduNewsReader;
import com.imaginea.dc.newsreaders.NyTimesNewsReader;
import com.imaginea.dc.service.NewsReaderService;

public class RSSFeedJob {

	private BBCNewsReader bbcNewsReader;
	private HinduNewsReader hinduNewsReader;
	private NyTimesNewsReader nyTimesNewsReader;
	
	private NewsReaderService newsReaderService;

	/* RSS Feeds */

	public List<NewsArticle> fetchNewsFromRSSFeeds() {
		
		System.out.println("----------------------------fetchNewsFromRSSFeeds--------------------------");

		List<NewsArticle> articles = new ArrayList<NewsArticle>();

		try {
			List<NewsArticle> bbcfeeds = bbcNewsReader.processRSSfeed(null);
			if (bbcfeeds != null)
				articles.addAll(bbcfeeds);

			List<NewsArticle> hindufeeds = hinduNewsReader.processRSSfeed(null);
			if (hindufeeds != null)
				articles.addAll(hindufeeds);

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (NewsArticle newsArticle : articles) {
			newsReaderService.createNewsArticle(newsArticle);
		}

		return articles;
	}
	
	
	/* Getters and Setters */

	public void setBbcNewsReader(BBCNewsReader bbcNewsReader) {
		this.bbcNewsReader = bbcNewsReader;
	}

	public void setHinduNewsReader(HinduNewsReader hinduNewsReader) {
		this.hinduNewsReader = hinduNewsReader;
	}

	public void setNyTimesNewsReader(NyTimesNewsReader nyTimesNewsReader) {
		this.nyTimesNewsReader = nyTimesNewsReader;
	}

	public void setNewsReaderService(NewsReaderService newsReaderService) {
		this.newsReaderService = newsReaderService;
	}

}
