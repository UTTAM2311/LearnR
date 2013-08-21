package com.imaginea.dc.preprocess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imaginea.dc.jobs.RSSFeedJob;
import com.imaginea.dc.preprocesor.PreprocessData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class TestPreprocessData {

	@Autowired
	private PreprocessData preprocessData;

	@Test
	public void testPreprocessData() throws Exception {

		preprocessData.preprocessData();
	}

}
