package com.dc.rss;

import java.net.URL;
import java.util.Iterator;

import org.junit.Test;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ReaderTest {

	@Test
	public void fetchRSSFeedsTest() throws Exception {
		
		String hindu = "http://www.hindu.com/rss/01hdline.xml";
		String bbcWorld = "http://feeds.bbci.co.uk/news/world/rss.xml";

		URL url = new URL(bbcWorld);
		XmlReader reader = null;

		try {

			reader = new XmlReader(url);
			SyndFeed feed = new SyndFeedInput().build(reader);
			System.out.println("Feed Title: " + feed.getAuthor());

			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				SyndEntry entry = (SyndEntry) i.next();
				System.out.println(entry.getTitle());
			}
			
		} finally {
			if (reader != null)
				reader.close();
		}

	}

}
