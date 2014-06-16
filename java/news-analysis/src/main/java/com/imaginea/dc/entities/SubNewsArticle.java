package com.imaginea.dc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table( name = "sub_news_articles", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "url" })
})
public class SubNewsArticle extends BaseNewsArticle {
	
	private NewsArticle newsArticle;
	
	/* Constructors */
	
	public SubNewsArticle() {
		super();
	}
	
	public SubNewsArticle(NewsArticle newsArticle) {
		super();
		this.newsArticle = newsArticle;
		
		// Base Properties
		
		super.isPositive = newsArticle.isPositive;
		super.deathCount = newsArticle.deathCount;
		
		super.cause = newsArticle.cause;
		
		super.location = newsArticle.location;
		super.publishedDate = newsArticle.publishedDate;
		super.updatedDate = newsArticle.updatedDate;

		super.source = newsArticle.source;
		
		super.publisher = newsArticle.publisher;
		super.author = newsArticle.author;

		super.url = newsArticle.url;

		super.title = newsArticle.title;
		super.description = newsArticle.description;
		super.content = newsArticle.content;
		
		super.uniqueValue = newsArticle.uniqueValue;
		
	}
	
	
	/* Getters and Setters */

	@ManyToOne
	@JoinColumn(name = "news_article_pkey", nullable = false)
	public NewsArticle getNewsArticle() {
		return newsArticle;
	}

	public void setNewsArticle(NewsArticle newsArticle) {
		this.newsArticle = newsArticle;
	}
	
}
