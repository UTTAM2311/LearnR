package com.imaginea.dc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.service.NewsArticleService;
import com.imaginea.dc.utils.Constants;

@Controller
@RequestMapping("/feed")
public class NewsArticleController {

	private NewsArticleService newsArticleService;
	
	private List<NewsArticle> newsArticles;
	private NewsArticle newsArticle;
	
	@RequestMapping(value = "/list")
	public String prepareArticlesList(Model model, 
			@RequestParam(required = false) Integer page, 
			@RequestParam(required = false) Integer size) {
		
		page = (page == null || page < 1) ? Constants.DEFAULT_PAGE_NUMBER : page;
		size = (size == null || size < 1) ? Constants.DEFAULT_PAGE_SIZE : size;
		
		newsArticles = newsArticleService.fetchAllArticles(page, size);

		// model attributes
		model.addAttribute("newsArticles", newsArticles);

		// model attributes for forms
		model.addAttribute("newsArticle", newsArticle);
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);

		return "news_article_list";
	}
	
	@RequestMapping(value = "/unlabelled")
	public String prepareUnlabelledArticlesList(Model model,
			@RequestParam(required = false) Integer page, 
			@RequestParam(required = false) Integer size) {
		
		page = (page == null || page < 1) ? Constants.DEFAULT_PAGE_NUMBER : page;
		size = (size == null || size < 1) ? Constants.DEFAULT_PAGE_SIZE : size;
		
		newsArticles = newsArticleService.fetchAllUnlabelledArticles(page, size);

		// model attributes
		model.addAttribute("newsArticles", newsArticles);

		// model attributes for forms
		model.addAttribute("newsArticle", newsArticle);
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		
		return "news_article_list";
	}
	
	@RequestMapping(value = "/labelled")
	public String prepareLabelledArticlesList(Model model,
			@RequestParam(required = false) Integer page, 
			@RequestParam(required = false) Integer size) {
		
		page = (page == null || page < 1) ? Constants.DEFAULT_PAGE_NUMBER : page;
		size = (size == null || size < 1) ? Constants.DEFAULT_PAGE_SIZE : size;
		
		newsArticles = newsArticleService.fetchArticlesForTraining(page, size);

		// model attributes
		model.addAttribute("newsArticles", newsArticles);

		// model attributes for forms
		model.addAttribute("newsArticle", newsArticle);
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		
		return "news_article_list";
	}
	
	
	@RequestMapping("/edit")
	public String editNewsArticle(Model model, @RequestParam Integer pkey) {
		if(pkey != null)
			newsArticle = newsArticleService.readNewsArticle(pkey);
		
		if(!model.containsAttribute("newsArticle")) {
			model.addAttribute("newsArticle", newsArticle);
		} 
		
		return "news_article_edit";

	}
	
	@RequestMapping("/save")
	public String editNewsArticle(Model model, NewsArticle newsArticle) {
		if(newsArticle != null) {
			if(newsArticle.getPkey() != null) {
				
				NewsArticle article = newsArticleService.readNewsArticle(newsArticle.getPkey());
				
				article.setContent(newsArticle.getContent());
				article.setIsPositive(newsArticle.getIsPositive());
				article.setDeathCount(newsArticle.getDeathCount());
				article.setCause(newsArticle.getCause());
				article.setLocation(newsArticle.getLocation());
				
				newsArticleService.updateNewsArticle(article);
			} else {
				newsArticleService.createNewsArticle(newsArticle);
			}
			
			newsArticle = newsArticleService.readNewsArticle(newsArticle.getPkey());
			
			if(!model.containsAttribute("newsArticle")) {
				model.addAttribute("newsArticle", newsArticle);
			} 
			
			Integer pkey = newsArticle.getPkey();
			Integer page = Math.round(pkey / Constants.DEFAULT_PAGE_SIZE) + 1;
			
			return this.prepareUnlabelledArticlesList(model, page, null);
			
		}
		
		return this.prepareUnlabelledArticlesList(model, null, null);
	}
	
	@RequestMapping("/view")
	public String viewNewsArticle(Model model, @RequestParam Integer pkey) {
		if(pkey != null) {
			newsArticle = newsArticleService.readNewsArticle(pkey);
		}
		
		model.addAttribute("newsArticle", newsArticle);
		
		return "news_article_view";
		
	}
	
	
	/* Getters and Setters */
	
	public void setNewsArticleService(NewsArticleService newsArticleService) {
		this.newsArticleService = newsArticleService;
	}
	
	
}
