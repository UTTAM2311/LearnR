package com.imaginea.dc.reuters;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ucar.nc2.ft.fmrc.FmrcInv.UberGrid;

import com.imaginea.dc.entities.BaseNewsArticle;
import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.entities.SubNewsArticle;
import com.imaginea.dc.newsproviders.ReutersNewsProvider;
import com.imaginea.dc.service.NewsArticleService;
import com.imaginea.dc.utils.HTMLLinkExtractor;
import com.imaginea.dc.utils.HtmlLink;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath:appContext.xml"
})
public class ReutersCrawlerTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReutersCrawlerTest.class);
	
	@Autowired
	private ReutersNewsProvider _reutersNewsProvider;
	
	@Autowired
	private NewsArticleService _newsReaderService;
	
	
	
	
	@Test
	public void crawlForHTMLAndSaveContentInFileTest() throws Exception {
		
		String pageUrlStr = "http://www.reuters.com/news/archive/worldNews?date=08012013";
		
		URL url = new URL(pageUrlStr);
		System.out.println("Content-Type: "	+ url.openConnection().getContentType());
		
		HTMLLinkExtractor htmlLinkExtractor = new HTMLLinkExtractor();
		
		Vector<HtmlLink> urls = htmlLinkExtractor.grabHTMLLinks(url);
		HtmlLink htmlLink;
		for (int i = 0; i < urls.size(); i++) {
			htmlLink = urls.get(i);
			final String link = htmlLink.getLink();
			
			if(link.contains("article")) {
				System.out.println("----- " + link + " -----");
				
				String unValue = link.substring(0, link.indexOf("-id"));
				System.out.println("----- Unique Value : " + unValue + " -----");
				
			}
		}
		
		// Write to file
//		writeURLtoFile(url, "test.html");
	}
	
	
	@Test
	public void dataDuplicateCleanUpTest() throws Exception {
		
		List<Integer> pkeysToDel = new ArrayList<Integer>();
		List<String> urlsToNote = new ArrayList<String>();
		
		List<NewsArticle> articles = _newsReaderService.fetchAllArticles(1, 15000);
		LOGGER.info("Total Articles Found : " + articles.size());
		Integer articlePkey;
		String url;
		String uniqueValue;
		
		NewsArticle parent;
		for (NewsArticle article : articles) {
			articlePkey = article.getPkey();
			if(article.getUniqueValue() == null) {
				url = article.getUrl();
				
				if(url.indexOf("-id") == -1) {
					uniqueValue = url;
					
					if(url.contains("reuters")) {
						LOGGER.info("interesting Url : " + url);
						urlsToNote.add(url);
					}
				} else {
					uniqueValue = url.substring(0, url.indexOf("-id"));
				}
				
				article.setUniqueValue(uniqueValue);
				
				parent = _newsReaderService.readNewsArticleByUniqueValue(uniqueValue);
				if(parent == null) {
					_newsReaderService.updateNewsArticle(article);
				} else {
					
					LOGGER.info("Duplicate Entry Found : Creating SubNewsArticle for article with Pkey : " + articlePkey);
					
					SubNewsArticle subArticle = new SubNewsArticle(article);
					subArticle.setNewsArticle(parent);
					
					_newsReaderService.createSubNewsArticle(subArticle);
					pkeysToDel.add(articlePkey);
					
				}
			}
		}
		
		for (Integer key : pkeysToDel) {
			_newsReaderService.deleteNewsArticle(key);
		}
	
		for (String toNote : urlsToNote) {
			LOGGER.info(" URL : " + toNote);
		}
		
		LOGGER.info("Total Duplicates / Subarticles : " + pkeysToDel.size() );
		LOGGER.info("Interesting urls size : " + urlsToNote.size());
		
	}

	@Test
	public void crawlArchivesAndSaveArticlesTest() throws Exception {
		Date startDate = new Date(1374085800000l); // Jul 18 onwards
		Date endDate = new Date();
		
		Date curDate = startDate;
		while (curDate.before(endDate)) {
			
			LOGGER.info("-----------------------------------------------------------------------");
			LOGGER.info("Fetching articles of date : " + curDate.toString());
			
			List<NewsArticle> articles = _reutersNewsProvider.fetchNewsArticlesByDate(curDate);
			if(articles != null && articles.size() > 0) {
				for (NewsArticle article : articles) {
					_newsReaderService.createNewsArticle(article);
				}
			}
			
			try {
				LOGGER.info("Waiting for 5 secs");
			    Thread.sleep(8000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			curDate = addDay(curDate);
		}
		
	}
	
	@Test
	public void testDateFormat() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		
		Date date = new Date();
		String dateString = dateFormat.format(date);
		System.out.println(dateString);
		
		date = addDay(date);
		dateString = dateFormat.format(date);
		System.out.println(dateString);
		
	}
	
	private static Date addDay(Date date) {
		if(date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, 1);
			return cal.getTime();
		}
		return null;
	}
	

}
