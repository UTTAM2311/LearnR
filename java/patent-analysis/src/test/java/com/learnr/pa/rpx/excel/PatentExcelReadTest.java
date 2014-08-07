package com.learnr.pa.rpx.excel;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.pa.rpx.excel.bean.PatAbstract;
import com.learnr.util.Verify;
import com.learnr.util.excel.GenericExcelReader;

public class PatentExcelReadTest {

	private static final Logger logger = LoggerFactory.getLogger(PatentExcelReadTest.class);

	// data sets paths
	private static final String BASE_DIR = "src/main/resources/sample-files-rpx/";

	private static final String PATS = BASE_DIR + "pats.xls";
	private static final String PAT_ABSTRACTS = BASE_DIR + "pat_abstracts.xlsx";
	private static final String PAT_DESCRIPTIONS = BASE_DIR + "pat_descriptions.xlsx";
	private static final String PAT_CLAIMS = BASE_DIR + "pat_claims.xlsx";
	

	@Before
	public void init() {

	}

	@After
	public void destroy() {

	}

	@Test
	public void read_pats_test() {
		
		File f = new File(PAT_ABSTRACTS);
		GenericExcelReader<PatAbstract> ger = new GenericExcelReader<PatAbstract>(f);
		List<PatAbstract> pats = ger.read(PatAbstract.class);
		
		logger.info("Total no of patents found : " + pats.size());
		
		Map<String, String> patAbsMap = this.getIdToAbstractTextMap(pats);
		List<String> abs = (List<String>) patAbsMap.values();
		
		// Cluster them
		
		
		
		
		
	}

	private Map<String, String> getIdToAbstractTextMap(List<PatAbstract> pats) {
		Verify.notEmpty(pats, "Empty or null");
		
		Map<String, String> paMap = new HashMap<String, String>();
		for (PatAbstract pa : pats) {
			if(pa == null)
				continue;
			
			paMap.put(pa.getPatentId(), pa.getAbstractText());
		}
		
		return paMap;
	}

}
