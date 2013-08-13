package com.dc.beans;

import java.util.Date;

public class NewsArticle {

	private Boolean isPositive;

	private String source;
	private String publisher;

	private String location;
	private Date date;

	private String url;

	private String headline;
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
	
	public NewsArticle(String source, String publisher, String url,
			String headline) {
		super();
		this.source = source;
		this.publisher = publisher;
		this.url = url;
		this.headline = headline;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
