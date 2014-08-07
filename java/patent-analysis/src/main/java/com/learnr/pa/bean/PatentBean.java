package com.learnr.pa.bean;

import java.util.List;

public class PatentBean {

	private Integer id;
	private String title;
	private String simpleAbstract;
	private String description;
	private List<String> claims;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSimpleAbstract() {
		return simpleAbstract;
	}

	public void setSimpleAbstract(String simpleAbstract) {
		this.simpleAbstract = simpleAbstract;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getClaims() {
		return claims;
	}

	public void setClaims(List<String> claims) {
		this.claims = claims;
	}

}
