package com.imaginea.dc.newsproviders;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imaginea.dc.api.NewsProvider;
import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.utils.HTMLLinkExtractor;
import com.imaginea.dc.utils.HtmlLink;

public class ReutersNewsProvider implements NewsProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReutersNewsProvider.class);
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
	private final String siteUrl = "http://www.reuters.com";
	private final String archiveBaseUrl = "http://www.reuters.com/news/archive/worldNews?date=";
	private final String sourceStr = "Reuters";

	private HTMLLinkExtractor htmlLinkExtractor;
	
	/* Implementations */
	
	public List<NewsArticle> processRSSfeed(String rssFeedUrl) 	throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public NewsArticle crawlUrlForNewsArticle(String articleUrl) throws IOException {
		LOGGER.info("Crawling URL : " + articleUrl);
		
		NewsArticle article = new NewsArticle(articleUrl);
		article.setSource(sourceStr);
		
		try {
			Document doc = Jsoup.connect(articleUrl).get();
			Elements elements;
			if(doc != null) {
				
				elements = doc.select(".column2 h1");
				article.setTitle(elements.text());
				
				elements = doc.select(".focusParagraph p");
				article.setDescription(elements.text());
				
				elements = doc.select("#articleText p");
				article.setContent(elements.text());
				
				LOGGER.info("Article title : " + article.getTitle());
				
			}
		} catch (Exception e) {
			LOGGER.error("Error while reading : " + articleUrl);
			LOGGER.error("----------- : " + e.getMessage());
		}
		
		return article;
	}
	
	
	
	/* Archive Related */
	
	public String prepareArchiveUrlByDate(Date date) {
		if(date != null) {
			String dateStr = dateFormat.format(date);
			return (archiveBaseUrl + dateStr);
		}
		return null;
	}

	public List<NewsArticle> fetchNewsArticlesByDate(Date date) throws IOException {
		List<NewsArticle> articles = new ArrayList<NewsArticle>();
		if(date != null) {
			String archiveUrl = this.prepareArchiveUrlByDate(date);
			URL url = new URL(archiveUrl);

			LOGGER.info("Archive URL : " + archiveUrl);
			
			Vector<HtmlLink> urls = htmlLinkExtractor.grabHTMLLinks(HTMLLinkExtractor.fetchHtmlFromURL(url));
			HtmlLink htmlLink;
			
			NewsArticle article;
			for (int i = 0; i < urls.size(); i++) {
				htmlLink = urls.get(i);
				if(htmlLink.getLink().contains("article")) {
					article = this.crawlUrlForNewsArticle(siteUrl + htmlLink.getLink());
					if(article != null) {
						article.setPublishedDate(date);
						articles.add(article);
					}
					
					try {
						LOGGER.info("Waiting for 1000 milliseconds");
					    Thread.sleep(1000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
				}
			}
			
			LOGGER.info("No. of Articles extracted : " + articles.size());
			
		}
		
		return articles;
	}


	/* Getters and Setters */
	
	public void setHtmlLinkExtractor(HTMLLinkExtractor htmlLinkExtractor) {
		this.htmlLinkExtractor = htmlLinkExtractor;
	}
	

}
