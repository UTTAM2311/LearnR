package com.imaginea.dc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.service.NewsReaderService;

@Controller
@RequestMapping("/feed")
public class NewsArticleController {

	private NewsReaderService newsReaderService;
	
	private List<NewsArticle> newsArticles;
	private NewsArticle newsArticle;
	
	@RequestMapping(value = "/list")
	public String prepareArticlesList(Model model) {
		
		newsArticles = newsReaderService.fetchAllArticles();

		// model attributes
		model.addAttribute("newsArticles", newsArticles);

		// model attributes for forms
		model.addAttribute("newsArticle", newsArticle);

		return "news_article_list";
	}
	
	@RequestMapping(value = "/unlabelled")
	public String prepareUnlabelledArticlesList(Model model) {
		
		newsArticles = newsReaderService.fetchAllUnlabelledArticles();

		// model attributes
		model.addAttribute("newsArticles", newsArticles);

		// model attributes for forms
		model.addAttribute("newsArticle", newsArticle);
		
		return "news_article_list";
	}
	
	@RequestMapping(value = "/labelled")
	public String prepareLabelledArticlesList(Model model) {
		
		newsArticles = newsReaderService.fetchArticlesForTraining();

		// model attributes
		model.addAttribute("newsArticles", newsArticles);

		// model attributes for forms
		model.addAttribute("newsArticle", newsArticle);
		
		return "news_article_list";
	}
	
	
	@RequestMapping("/edit")
	public String editNewsArticle(Model model, @RequestParam Integer pkey) {
		if(pkey != null)
			newsArticle = newsReaderService.readNewsArticle(pkey);
		
		if(!model.containsAttribute("newsArticle")) {
			model.addAttribute("newsArticle", newsArticle);
		} 
		
		return "news_article_edit";

	}
	
	@RequestMapping("/save")
	public String editNewsArticle(Model model, NewsArticle newsArticle) {
		if(newsArticle != null) {
			if(newsArticle.getPkey() != null) {
				
				NewsArticle article = newsReaderService.readNewsArticle(newsArticle.getPkey());
				
				article.setContent(newsArticle.getContent());
				article.setIsPositive(newsArticle.getIsPositive());
				article.setDeathCount(newsArticle.getDeathCount());
				article.setCause(newsArticle.getCause());
				article.setLocation(newsArticle.getLocation());
				
				newsReaderService.updateNewsArticle(article);
			} else {
				newsReaderService.createNewsArticle(newsArticle);
			}
		}
		
		newsArticle = newsReaderService.readNewsArticle(newsArticle.getPkey());
		
		if(!model.containsAttribute("newsArticle")) {
			model.addAttribute("newsArticle", newsArticle);
		} 
		
		return this.prepareUnlabelledArticlesList(model);
	}
	
	@RequestMapping("/view")
	public String viewNewsArticle(Model model, @RequestParam Integer pkey) {
		if(pkey != null) {
			newsArticle = newsReaderService.readNewsArticle(pkey);
		}
		
		model.addAttribute("newsArticle", newsArticle);
		
		return "news_article_view";
		
	}
	
	
	/* Getters and Setters */
	
	public void setNewsReaderService(NewsReaderService newsReaderService) {
		this.newsReaderService = newsReaderService;
	}
	
	
}
