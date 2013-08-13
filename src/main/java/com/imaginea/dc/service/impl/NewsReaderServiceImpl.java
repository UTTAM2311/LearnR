package com.imaginea.dc.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imaginea.dc.api.NewsReader;
import com.imaginea.dc.beans.NewsArticle;
import com.imaginea.dc.newsreaders.BBCNewsReader;
import com.imaginea.dc.newsreaders.HinduNewsReader;
import com.imaginea.dc.service.NewsReaderService;
import com.imaginea.dc.utils.ExcelUtil;

public class NewsReaderServiceImpl implements NewsReaderService {

	public List<NewsArticle> fetchNewsFromRSSFeeds() {
		
		List<NewsArticle> articles = new ArrayList<NewsArticle>();
		
		try {
			NewsReader bbcReader = new BBCNewsReader();
			List<NewsArticle> bbcfeeds = bbcReader.processRSSfeed(null);
			if(bbcfeeds != null)
				articles.addAll(bbcfeeds);
			
			
			NewsReader hinduReader = new HinduNewsReader();
			List<NewsArticle> hindufeeds = hinduReader.processRSSfeed(null);
			if(hindufeeds != null)
				articles.addAll(hindufeeds);
			
		
			/* File Export */
		
			Date today = new Date();
			long fileTitle = today.getTime();
			ExcelUtil.createXlsFromListOfArticles(articles, "Articles" + fileTitle + ".xlsx");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return articles;
	}

}
