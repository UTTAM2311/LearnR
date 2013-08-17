package com.imaginea.dc.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imaginea.dc.entities.NewsArticle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath:appContext.xml"
})
public class NewsReaderServiceTest {
	
	@Autowired
	private NewsReaderService _readerService;

	@Test
	public void testNewsArticlesToExcelExport() throws Exception {
		
//		List<NewsArticle> newsFeeds = _readerService.fetchNewsFromRSSFeeds();
//		Assert.assertNotNull(newsFeeds);
//		Assert.assertTrue(newsFeeds.size() > 0);
		
	}

	
	
}
