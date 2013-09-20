package com.imaginea.dc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imaginea.dc.preprocesor.PreprocessData;
import com.imaginea.dc.service.DataPreProcessingService;
import com.imaginea.dc.service.ModelBuildService;
import com.imaginea.dc.service.PredictService;
import com.imaginea.dc.svmutils.SVMProcessor;

@Controller
@RequestMapping("/svm")
public class SVMController {

	@Autowired
	private PredictService predictService;

	@Autowired
	private ModelBuildService modelBuildService;
	
	@RequestMapping("/predictvalues")
	public String predictValues(){
		predictService.startPredictingUnlabelledData(true, true);
		return "success";
	}
	
	@RequestMapping("/buildmodel")
	public String buildModel(){
		modelBuildService.startBuildingModel(true);
		return "success";
	}
	
}
