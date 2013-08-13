package com.imaginea.dc.newsreaders;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.imaginea.dc.api.NewsReader;
import com.imaginea.dc.beans.NewsArticle;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class BBCNewsReader implements NewsReader {
	
	private final String bbcRssFeedUrl = "http://feeds.bbci.co.uk/news/world/rss.xml";

	public List<NewsArticle> processRSSfeed(String rssFeedUrl) throws IOException {
		if(rssFeedUrl == null || rssFeedUrl.isEmpty()) {
			rssFeedUrl = this.bbcRssFeedUrl;
		}
		
		List<NewsArticle> articles = new ArrayList<NewsArticle>();
		XmlReader reader = null;
		
		try {
			
			URL url = new URL(rssFeedUrl);
			
			reader = new XmlReader(url);
			SyndFeed feed = new SyndFeedInput().build(reader);
			System.out.println("Feed Title: " + feed.getAuthor());

			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				SyndEntry entry = (SyndEntry) i.next();
				System.out.println(entry.getTitle());
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				reader.close();
		}
		
		return articles;
	}
	
	public NewsArticle crawlUrlForContent() {
		// TODO Auto-generated method stub
		return null;
	}

}
