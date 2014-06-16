package com.imaginea.dc.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imaginea.dc.mahout.classifier.MahoutNBClassifier;
import com.imaginea.dc.mahout.model.MahoutBinaryClassifierModelBuilder;
import com.imaginea.dc.mahout.model.MahoutCauseClassifierModelBuilder;
import com.imaginea.dc.service.NewsArticleService;
import com.imaginea.dc.utils.MessageUtil;

@Controller
@RequestMapping("/mahout")
public class MahoutController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MahoutController.class);
	
	private NewsArticleService newsArticleService;

	private MahoutBinaryClassifierModelBuilder mahoutBinaryClassifierModelBuilder;
	
	private MahoutCauseClassifierModelBuilder mahoutCauseClassifierModelBuilder;
	
	@ResponseBody
	@RequestMapping(value = "/ping")
	public String ping() {	
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/model1/build")
	public String buildModel1() {	
		mahoutBinaryClassifierModelBuilder.buildInputBinaryClassifierModel();
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/model1/classify", method=RequestMethod.POST )
	public String classifyModel1(@RequestParam("text") String text) {	
		try {
			return getModel1Classifier().classify(text);
		} catch (IOException e) {
			return e.getMessage();
		}
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/model2/build", method=RequestMethod.GET)
	public String buildModel2() {	
		mahoutBinaryClassifierModelBuilder.buildInputBinaryClassifierModel();
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/model2/classify")
	public String classifyModel2(@RequestParam("text") String text) {	
		try {
			return getModel2Classifier().classify(text);
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	
	/* Getters and Setters */
	
	public void setNewsArticleService(NewsArticleService newsArticleService) {
		this.newsArticleService = newsArticleService;
	}


	public void setMahoutBinaryClassifierModelBuilder(MahoutBinaryClassifierModelBuilder mahoutBinaryClassifierModelBuilder) {
		this.mahoutBinaryClassifierModelBuilder = mahoutBinaryClassifierModelBuilder;
	}


	public void setMahoutCauseClassifierModelBuilder(MahoutCauseClassifierModelBuilder mahoutCauseClassifierModelBuilder) {
		this.mahoutCauseClassifierModelBuilder = mahoutCauseClassifierModelBuilder;
	}
	
	private MahoutNBClassifier getModel1Classifier() {
		String vectorLocation = MessageUtil.getMessage("model1.local.output.vector.filedir");
		String localBaseDir = MessageUtil.getMessage("local.base.dir");
		MahoutNBClassifier classifier = new MahoutNBClassifier(localBaseDir+MessageUtil.getMessage("model1.local.output.model.filedir"),
				localBaseDir+MessageUtil.getMessage("model1.local.output.model.filedir")+"label-index", 
				localBaseDir+vectorLocation+"dictionary.file-0", 
				localBaseDir+vectorLocation+"frequency.file-0", 
				MessageUtil.getMessage("model1.lucene.analyser"));
		return classifier;
	}
	
	private MahoutNBClassifier getModel2Classifier() {
		String vectorLocation = MessageUtil.getMessage("model1.local.output.vector.filedir");
		String localBaseDir = MessageUtil.getMessage("local.base.dir");
		MahoutNBClassifier classifier = new MahoutNBClassifier(localBaseDir+MessageUtil.getMessage("model2.local.output.model.filedir"),
				localBaseDir+MessageUtil.getMessage("model2.local.output.model.filedir")+"label-index", 
				localBaseDir+vectorLocation+"dictionary.file-0", 
				localBaseDir+vectorLocation+"frequency.file-0", 
				localBaseDir+MessageUtil.getMessage("model2.lucene.analyser"));
		return classifier;
	}
}
