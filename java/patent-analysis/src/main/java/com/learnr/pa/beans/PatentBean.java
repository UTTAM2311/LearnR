package com.learnr.pa.beans;

import java.util.List;

public class PatentBean {

	private Integer id;
	private String patentNumber;
	private String title;
	private String pAbstract;
	private String description;
	private List<String> claims;

	
	/* --- Getters and Setters --- */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPatentNumber() {
		return patentNumber;
	}

	public void setPatentNumber(String patentNumber) {
		this.patentNumber = patentNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstract() {
		return pAbstract;
	}

	public void setAbstract(String simpleAbstract) {
		this.pAbstract = simpleAbstract;
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
