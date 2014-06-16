package com.imaginea.dc.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseNewsArticle extends BaseEntity {
	
	protected Boolean isPositive;
	protected Integer deathCount;
	
	protected Cause cause;
	
	protected String location;
	protected Date publishedDate;
	protected Date updatedDate;

	protected String source;
	
	protected String publisher;
	protected String author;

	protected String url;

	protected String title;
	protected String description;
	protected String content;
	
	protected String uniqueValue;
	
	
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

	@Column(name = "url", nullable = false)
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

	@Column(name = "unique_value" )
	public String getUniqueValue() {
		return uniqueValue;
	}

	public void setUniqueValue(String uniqueValue) {
		this.uniqueValue = uniqueValue;
	}
	
}
