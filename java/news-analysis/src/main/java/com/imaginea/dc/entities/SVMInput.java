package com.imaginea.dc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "svm_input")
@NamedQueries({
	@NamedQuery(name = "svm_input.deleteall", 
			query = "delete from SVMInput"),
	@NamedQuery(name = "svm_input.fetchinputline", 
			query = "select inputLine from SVMInput"),
	@NamedQuery(name = "svm_input.fetchoutpuvalues", 
			query = "select outputValue from SVMInput")
})

public class SVMInput extends BaseEntity {

	private String inputLine;
	
	private String outputValue;
	
	private NewsArticle newsArticle;

	@Lob
	public String getInputLine() {
		return inputLine;
	}

	public void setInputLine(String inputLine) {
		this.inputLine = inputLine;
	}

	public String getOutputValue() {
		return outputValue;
	}

	public void setOutputValue(String outputValue) {
		this.outputValue = outputValue;
	}

	@OneToOne
	@JoinColumn(name="newsarticle_pkey")
	public NewsArticle getNewsArticle() {
		return newsArticle;
	}

	public void setNewsArticle(NewsArticle newsArticle) {
		this.newsArticle = newsArticle;
	}

}
