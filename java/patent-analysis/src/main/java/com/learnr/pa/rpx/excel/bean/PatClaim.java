package com.learnr.pa.rpx.excel.bean;

import com.learnr.util.excel.ExcelColumn;
import com.learnr.util.excel.IExcelEntity;

public class PatClaim implements IExcelEntity {
	
	@ExcelColumn(name = "id")
	private String id;

	@ExcelColumn(name = "pat_id")
	private String patentId;
	
	@ExcelColumn(name = "claim_num")
	private String claimNumber;

	@ExcelColumn(name = "claim_text")
	private String claimText;

	@ExcelColumn(name = "word_count")
	private String wordCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatentId() {
		return patentId;
	}

	public void setPatentId(String patentId) {
		this.patentId = patentId;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimText() {
		return claimText;
	}

	public void setClaimText(String claimText) {
		this.claimText = claimText;
	}

	public String getWordCount() {
		return wordCount;
	}

	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}

	@Override
	public String toString() {
		return "PatClaim [patentId=" + patentId + ", claimNumber=" + claimNumber + ", wordCount=" + wordCount + "]";
	}
	
}
