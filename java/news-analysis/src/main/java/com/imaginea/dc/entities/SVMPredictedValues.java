package com.imaginea.dc.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "svm_predicted_values")
public class SVMPredictedValues extends BaseEntity {

	private NewsArticle newsArticle;
	private double predictedValue;
	
	@OneToOne
	@JoinColumn(name="newsarticle_pkey")
	public NewsArticle getNewsArticle() {
		return newsArticle;
	}
	public void setNewsArticle(NewsArticle newsArticle) {
		this.newsArticle = newsArticle;
	}
	public double getPredictedValue() {
		return predictedValue;
	}
	public void setPredictedValue(double predictedValue) {
		this.predictedValue = predictedValue;
	}
	

}
