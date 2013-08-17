package com.imaginea.dc.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.jobs.RSSFeedJob;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath:appContext.xml"
})
public class NewsReaderServiceTest {
	
	@Autowired
	private NewsReaderService _readerService;
	
	@Autowired
	private RSSFeedJob rssFeed;

	@Test
	public void testNewsArticlesToExcelExport() throws Exception {
		
//		List<NewsArticle> newsFeeds = _readerService.fetchNewsFromRSSFeeds();
//		Assert.assertNotNull(newsFeeds);
//		Assert.assertTrue(newsFeeds.size() > 0);
		
		rssFeed.fetchNewsFromRSSFeeds();
		
	}

	
	
}
