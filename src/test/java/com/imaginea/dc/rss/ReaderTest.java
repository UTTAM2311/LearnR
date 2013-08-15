package com.imaginea.dc.rss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.lucene.analysis.CharReader;
import org.apache.lucene.analysis.charfilter.HTMLStripCharFilter;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.imaginea.dc.entities.NewsArticle;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ReaderTest {

	@Test
	public void fetchRSSFeedsTest() throws Exception {
		
		String hindu = "http://www.thehindu.com/news/international/?service=rss";
		String bbcWorld = "http://feeds.bbci.co.uk/news/world/rss.xml";
		String googleRss = "https://news.google.com/news/feeds?pz=1&cf=all&ned=in&hl=en&output=rss";
		
		URL url = new URL(hindu);
		XmlReader reader = null;

		try {

			reader = new XmlReader(url);
			SyndFeed feed = new SyndFeedInput().build(reader);
			System.out.println("Feed Title: " + feed.getAuthor());

			SyndEntry entry;
			SyndContent content;
			
			NewsArticle article;
			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				entry = (SyndEntry) i.next();
				content = entry.getDescription();
				
				article = new NewsArticle();
				article.setTitle(entry.getTitle());
				
				
				System.out.println("-------------------------------------------------------------");
				System.out.println("Title : " + entry.getTitle());
				
				
				System.out.println("Description : " + content.getValue());
				
				System.out.println("URI : " + entry.getUri());
				System.out.println("Contents : " + entry.getContents());
				System.out.println("Date : " + entry.getPublishedDate());
				
				
				
			}
			
		} finally {
			if (reader != null)
				reader.close();
		}

	}
	
	@Test
	public void crawlForContentTest() throws Exception {
		
//		String pageUrlStr = "http://www.bbc.co.uk/news/world-latin-america-23166213";
//		String pageUrlStr = "http://www.nytimes.com/2007/12/31/world/africa/31kenya.html";
		String pageUrlStr = "http://www.thehindu.com/news/international/world/suicide-bombing-kills-24-at-iraqi-cafe/article5018301.ece";
		
		URL pageUrl = new URL(pageUrlStr);
		
		URLConnection uconn = pageUrl.openConnection();
		HttpURLConnection conn = (HttpURLConnection) uconn;
		BufferedReader pageHtml = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		// Extract days
		boolean slicing = false;

		String htmlLine = "";
		String postHtml = "";
		while ((htmlLine = pageHtml.readLine()) != null) {
			htmlLine.trim();
			if (!htmlLine.isEmpty()) {
				if (htmlLine.contains("<div class=\"article-text\">")) {
					slicing = true;
					postHtml = "";
				}
				
				if (htmlLine.endsWith("<div class=\"noline\"></div>")) {
					slicing = false;
				}
				
				if (slicing) {
					postHtml += htmlLine;
				}
			}
		}
		
		StringBuilder out = new StringBuilder();
		StringReader strReader = new StringReader(postHtml);
		try {
			HTMLStripCharFilter html = new HTMLStripCharFilter(
					CharReader.get(strReader.markSupported() ? strReader : new BufferedReader(strReader)));
			char[] cbuf = new char[1024 * 10];
			while (true) {
				int count = html.read(cbuf);
				if (count == -1)
					break; // end of stream mark is -1
				if (count > 0)
					out.append(cbuf, 0, count);
			}
			html.close();
		} catch (IOException e) {
			throw e;
		}

		postHtml = out.toString();
		
		System.out.println(postHtml);
		
		
	}
	
	@Test
	public void crawlForContentDomTest() throws Exception {
		
		
		URL pageUrl = new URL("http://www.bbc.co.uk/news/world-latin-america-23166213");
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(pageUrl.openStream());
		Element mainContent = (Element) doc.getElementById("main-content");
//		NodeList nodes = 
		System.out.println(mainContent.getElementCount() + " nodes found");
		
		
	}
	
	
	
	
	

}
