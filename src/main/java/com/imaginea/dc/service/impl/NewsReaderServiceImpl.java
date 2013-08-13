package com.imaginea.dc.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imaginea.dc.api.NewsReader;
import com.imaginea.dc.beans.NewsArticle;
import com.imaginea.dc.constants.NewsSource;
import com.imaginea.dc.newsreaders.BBCNewsReader;
import com.imaginea.dc.service.NewsReaderService;
import com.imaginea.dc.utils.ExcelUtil;

public class NewsReaderServiceImpl implements NewsReaderService {

	public List<NewsArticle> fetchNewsFromRSSFeeds() {
		
		List<NewsArticle> articles = new ArrayList<NewsArticle>();
		
		NewsReader bbcReader = new BBCNewsReader();
		try {
			List<NewsArticle> bbcArticles = bbcReader.processRSSfeed(null);
			if(bbcArticles != null)
				articles.addAll(bbcArticles);
			
			
			Date today = new Date();
			long fileTitle = today.getTime();
			ExcelUtil.createXlsFromListOfArticles(articles, NewsSource.BBC + fileTitle + ".xlsx");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return articles;
	}

}
