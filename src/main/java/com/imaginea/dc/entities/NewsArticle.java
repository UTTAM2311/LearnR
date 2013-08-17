package com.imaginea.dc.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table( name = "news_articles", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "title", "published_date" })
})
@NamedQueries({
	@NamedQuery(name = "newsArticle.fetchByTitle", query = "SELECT instance FROM NewsArticle instance WHERE instance.title = :title"),
	@NamedQuery(name = "newsArticle.fetchAllUnblabelled", query = "SELECT instance FROM NewsArticle instance WHERE instance.isPositive IS NULL"),
	@NamedQuery(name = "newsArticle.fetchLabelled", query = "SELECT instance FROM NewsArticle instance WHERE instance.isPositive IS NOT NULL"),
	@NamedQuery(name = "newsArticle.fetchBySource", query = "SELECT instance FROM NewsArticle instance WHERE instance.source = :source ")
})
public class NewsArticle extends BaseEntity {

	private Boolean isPositive;
	private Integer deathCount;
	
	private Cause cause;
	
	private String location;
	private Date publishedDate;
	private Date updatedDate;

	private String source;
	
	private String publisher;
	private String author;

	private String url;

	private String title;
	private String description;
	private String content;
	

	/* Constructors */

	public NewsArticle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NewsArticle(String url) {
		super();
		this.url = url;
	}
	
	public NewsArticle(String source, String publisher, String url, String title) {
		super();
		this.source = source;
		this.publisher = publisher;
		this.url = url;
		this.title = title;
	}

	/* Getters and Setters */

	@Column(name = "is_positive")
	public Boolean getIsPositive() {
		return isPositive;
	}

	public void setIsPositive(Boolean isPositive) {
		this.isPositive = isPositive;
	}

	@Column(name = "death_count")
	public Integer getDeathCount() {
		return deathCount;
	}

	public void setDeathCount(Integer deathCount) {
		this.deathCount = deathCount;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "cause")
	public Cause getCause() {
		return cause;
	}

	public void setCause(Cause cause) {
		this.cause = cause;
	}

	@Column(name = "location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "published_date")
	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "publisher")
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", columnDefinition = "TEXT")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "content", columnDefinition = "TEXT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
