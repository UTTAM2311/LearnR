package com.imaginea.dc.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.newsproviders.BBCNewsProvider;
import com.imaginea.dc.newsproviders.HinduNewsProvider;
import com.imaginea.dc.newsproviders.ReutersNewsProvider;
import com.imaginea.dc.service.NewsArticleService;

public class RSSFeedJob {

	private NewsArticleService newsArticleService;

	private ReutersNewsProvider reutersNewsProvider;
	private BBCNewsProvider bbcNewsProvider;
	private HinduNewsProvider hinduNewsProvider;
	
	
	/* RSS Feeds */

	public List<NewsArticle> fetchNewsFromRSSFeeds() {
		
		System.out.println("----------------------------fetchNewsFromRSSFeeds--------------------------");

		List<NewsArticle> articles = new ArrayList<NewsArticle>();

		try {
			List<NewsArticle> bbcfeeds = bbcNewsProvider.processRSSfeed(null);
			if (bbcfeeds != null)
				articles.addAll(bbcfeeds);

			List<NewsArticle> hindufeeds = hinduNewsProvider.processRSSfeed(null);
			if (hindufeeds != null)
				articles.addAll(hindufeeds);
			
			List<NewsArticle> reutersfeeds = reutersNewsProvider.processRSSfeed(null);
			if (reutersfeeds != null)
				articles.addAll(reutersfeeds);

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (NewsArticle newsArticle : articles) {
			newsArticleService.createNewsArticle(newsArticle);
		}

		return articles;
	}
	
	
	/* Getters and Setters */


	public void setNewsArticleService(NewsArticleService newsArticleService) {
		this.newsArticleService = newsArticleService;
	}


	public void setReutersNewsProvider(ReutersNewsProvider reutersNewsProvider) {
		this.reutersNewsProvider = reutersNewsProvider;
	}

	public void setBbcNewsProvider(BBCNewsProvider bbcNewsProvider) {
		this.bbcNewsProvider = bbcNewsProvider;
	}

	public void setHinduNewsProvider(HinduNewsProvider hinduNewsProvider) {
		this.hinduNewsProvider = hinduNewsProvider;
	}

}
