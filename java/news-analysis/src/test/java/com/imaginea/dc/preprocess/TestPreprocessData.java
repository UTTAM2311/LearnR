package com.imaginea.dc.preprocess;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imaginea.dc.entities.NewsArticle;
import com.imaginea.dc.jobs.RSSFeedJob;
import com.imaginea.dc.preprocesor.PreprocessData;
import com.imaginea.dc.service.DataPreProcessingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class TestPreprocessData {

	@Autowired
	private PreprocessData preprocessData;
	
	@Autowired
	private DataPreProcessingService dataPreprocessingService;


	@Test
	public void testPreprocessData() throws Exception {

		List<NewsArticle>newsArticle = dataPreprocessingService.fetchTrainingDataFromDB();
		preprocessData.preprocessData(newsArticle);
		
	}

}
