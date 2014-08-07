package com.learnr.pa.rpx.excel.bean;

import com.learnr.util.excel.ExcelColumn;
import com.learnr.util.excel.IExcelEntity;

public class PatDescription implements IExcelEntity {

	@ExcelColumn(name = "id")
	private String id;
	
	@ExcelColumn(name = "pat_id")
	private String patentId;
	
	@ExcelColumn(name = "description")
	private String description;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWordCount() {
		return wordCount;
	}

	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}

	@Override
	public String toString() {
		return "PatDescription [patentId=" + patentId + ", wordCount=" + wordCount + "]";
	}
	
}
