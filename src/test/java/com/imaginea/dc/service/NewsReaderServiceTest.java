package com.imaginea.dc.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.imaginea.dc.beans.NewsArticle;
import com.imaginea.dc.service.impl.NewsReaderServiceImpl;
import com.imaginea.dc.utils.ExcelUtil;

public class NewsReaderServiceTest {
	
	NewsReaderService _readerService = new NewsReaderServiceImpl();

	@Test
	public void testNewsArticlesToExcelExport() throws Exception {
		
		List<NewsArticle> newsFeeds = _readerService.fetchNewsFromRSSFeeds();
		Assert.assertNotNull(newsFeeds);
		Assert.assertTrue(newsFeeds.size() > 0);
		
	}
	
}
