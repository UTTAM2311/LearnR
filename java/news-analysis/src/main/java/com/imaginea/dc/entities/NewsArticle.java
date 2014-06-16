package com.imaginea.dc.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table( name = "news_articles", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "url" }),
	@UniqueConstraint(columnNames = { "unique_value" })
})
@NamedQueries({
	@NamedQuery(name = "newsArticle.fetchByTitle", query = "SELECT instance FROM NewsArticle instance WHERE instance.title = :title"),
	@NamedQuery(name = "newsArticle.fetchByUniqueValue", query = "SELECT instance FROM NewsArticle instance WHERE instance.uniqueValue = :uniqueValue"),
	
	@NamedQuery(name = "newsArticle.fetchAllUnblabelled", query = "SELECT instance FROM NewsArticle instance WHERE instance.isPositive IS NULL"),
	@NamedQuery(name = "newsArticle.fetchLabelled", query = "SELECT instance FROM NewsArticle instance WHERE instance.isPositive IS NOT NULL"),
	@NamedQuery(name = "newsArticle.fetchBySource", query = "SELECT instance FROM NewsArticle instance WHERE instance.source = :source ")
})
public class NewsArticle extends BaseNewsArticle {

	/* Constructors */

	public NewsArticle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NewsArticle(String url) {
		super();
		super.url = url;
	}
	
	public NewsArticle(String source, String publisher, String url, String title) {
		super();
		super.source = source;
		super.publisher = publisher;
		super.url = url;
		super.title = title;
	}

}
