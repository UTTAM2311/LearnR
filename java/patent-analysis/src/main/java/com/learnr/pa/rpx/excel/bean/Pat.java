package com.learnr.pa.rpx.excel.bean;

import com.learnr.util.excel.ExcelColumn;
import com.learnr.util.excel.IExcelEntity;

public class Pat implements IExcelEntity {

	@ExcelColumn(name = "id")
	private String id;
	
	@ExcelColumn(name = "patnum")
	private String patentNumber;
	
	@ExcelColumn(name = "title")
	private String title;
	
	@ExcelColumn(name = "app_filing_date")
	private String appFilingDate;
	
	@ExcelColumn(name = "field_of_search")
	private String fieldOfSearch;
	
	@ExcelColumn(name = "us_class_current")
	private String currentUsClass;
	
	@ExcelColumn(name = "intl_class")
	private String internationalClass;
	
	@ExcelColumn(name = "publication_number")
	private String publicationNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatentNumber() {
		return patentNumber;
	}

	public void setPatentNumber(String patentNumber) {
		this.patentNumber = patentNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAppFilingDate() {
		return appFilingDate;
	}

	public void setAppFilingDate(String appFilingDate) {
		this.appFilingDate = appFilingDate;
	}

	public String getFieldOfSearch() {
		return fieldOfSearch;
	}

	public void setFieldOfSearch(String fieldOfSearch) {
		this.fieldOfSearch = fieldOfSearch;
	}

	public String getCurrentUsClass() {
		return currentUsClass;
	}

	public void setCurrentUsClass(String currentUsClass) {
		this.currentUsClass = currentUsClass;
	}

	public String getInternationalClass() {
		return internationalClass;
	}

	public void setInternationalClass(String internationalClass) {
		this.internationalClass = internationalClass;
	}

	public String getPublicationNumber() {
		return publicationNumber;
	}

	public void setPublicationNumber(String publicationNumber) {
		this.publicationNumber = publicationNumber;
	}

	@Override
	public String toString() {
		return "Pat [id=" + id + ", patentNumber=" + patentNumber + ", title=" + title + ", currentUsClass="
				+ currentUsClass + ", internationalClass=" + internationalClass + "]";
	}
	
	
}
