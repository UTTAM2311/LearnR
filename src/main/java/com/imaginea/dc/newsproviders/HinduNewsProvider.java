package com.imaginea.dc.newsproviders;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.imaginea.dc.api.NewsProvider;
import com.imaginea.dc.constants.NewsSource;
import com.imaginea.dc.entities.NewsArticle;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class HinduNewsProvider implements NewsProvider {
	
	private final String hinduRssFeedUrl = "http://www.thehindu.com/news/international/?service=rss";

	public List<NewsArticle> processRSSfeed(String rssFeedUrl)
			throws IOException {
		if(rssFeedUrl == null || rssFeedUrl.isEmpty()) {
			rssFeedUrl = this.hinduRssFeedUrl;
		}
		
		System.out.println("BBC RSS Feed URL : " + rssFeedUrl);
		
		List<NewsArticle> articles = new ArrayList<NewsArticle>();
		XmlReader reader = null;
		
		try {
			URL url = new URL(rssFeedUrl);
			reader = new XmlReader(url);
			
			SyndFeed feed = new SyndFeedInput().build(reader);
			System.out.println("Feed Title: " + feed.getAuthor());
			
			System.out.println("total feeds count : " + feed.getEntries().size());

			SyndEntry entry;
			SyndContent content;
			
			NewsArticle article;
			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				entry = (SyndEntry) i.next();
				content = entry.getDescription();
				
				article = new NewsArticle();
				
				article.setSource(NewsSource.Hindu);
				article.setPublisher(NewsSource.Hindu);
				article.setAuthor(entry.getAuthor());
				
				article.setPublishedDate(entry.getPublishedDate());
				article.setUpdatedDate(entry.getUpdatedDate());
				
				article.setUrl(entry.getUri());
				article.setTitle(entry.getTitle());
				article.setDescription(content.getValue());
				
				// Content
				if(article.getUrl() != null) {
//					article.setContent(this.crawlUrlForContent(article.getUrl()));
				}
				
				articles.add(article);
				
				System.out.println("---------------------------------------------------------------");
				System.out.println("Feed Title : " + entry.getTitle());
				
				
			}			
			
		} catch (FeedException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		
		return articles;
	}

	public NewsArticle crawlUrlForNewsArticle(String articleUrl)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String prepareArchiveUrlByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<NewsArticle> fetchNewsArticlesByDate(Date date)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
