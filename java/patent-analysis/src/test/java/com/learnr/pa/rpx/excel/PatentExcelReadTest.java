package com.learnr.pa.rpx.excel;

import org.junit.Test;

public class PatentExcelReadTest {
	
	@Test
	public void readExcelFile() {
		
		PatentExcelUtil.readPatsExcel(PatentExcelType.PAT_ABSTRACTS);
		
	}

}
