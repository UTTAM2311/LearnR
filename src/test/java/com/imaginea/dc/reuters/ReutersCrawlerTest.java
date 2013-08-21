package com.imaginea.dc.reuters;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
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

import com.imaginea.dc.entities.NewsArticle;
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
	
	/*
	public void setReutersNewsProvider(ReutersNewsProvider reutersNewsProvider) {
		this._reutersNewsProvider = reutersNewsProvider;
	}
	*/
	
	
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
	public void crawlForHTMLAndSaveContentInFileTest() throws Exception {
		
		String pageUrlStr = "http://www.reuters.com/news/archive/worldNews?date=08012013";
		
		URL url = new URL(pageUrlStr);
		System.out.println("Content-Type: "	+ url.openConnection().getContentType());
		
		HTMLLinkExtractor htmlLinkExtractor = new HTMLLinkExtractor();
		
		Vector<HtmlLink> urls = htmlLinkExtractor.grabHTMLLinks(getURL(url));
		HtmlLink htmlLink;
		for (int i = 0; i < urls.size(); i++) {
			htmlLink = urls.get(i);
			if(htmlLink.getLink().contains("article"))
				System.out.println("----- " + htmlLink.getLink() + " -----");
		}
		
		// Write to file
//		writeURLtoFile(url, "test.html");
	}
	
	private static String getURL(URL url) throws IOException {
		StringWriter sw = new StringWriter();
		BufferedInputStream in = new BufferedInputStream(url.openStream());
		for (int c = in.read(); c != -1; c = in.read()) {
			sw.write(c);
		}
		return sw.toString();
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
