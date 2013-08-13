package com.imaginea.dc.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.imaginea.dc.beans.NewsArticle;

public class ExcelUtilTest {

	
	@Test
	public void testExcelExport() throws Exception {
		
		NewsArticle art1 = new NewsArticle("BBC", "BBC", "BBC", "BBC");
		NewsArticle art2 = new NewsArticle("BBC1", "BBC1", "BBC1", "BBC1");
		
		List<NewsArticle> articles = new ArrayList<NewsArticle>();
		articles.add(art1);
		articles.add(art2);
		
		ExcelUtil.createXlsFromListOfArticles(articles, "sample.xlsx");
		
	}
	

}
