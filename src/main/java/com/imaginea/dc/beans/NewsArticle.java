package com.imaginea.dc.beans;

import java.util.Date;

public class NewsArticle {

	private Boolean isPositive;

	private String source;
	
	private String publisher;
	private String author;

	private String location;
	private Date publishedDate;
	private Date updatedDate;

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

	public Boolean getIsPositive() {
		return isPositive;
	}

	public void setIsPositive(Boolean isPositive) {
		this.isPositive = isPositive;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
