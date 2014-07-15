package com.learnr.pa.rpx.excel;

import java.util.HashMap;

public enum PatentExcelType {
	PATS, PAT_DESCRIPTIONS, PAT_CLAIMS, PAT_ABSTRACTS;

	public HashMap getExcelCellMapping() {
		switch (this) {
			case PATS:
	
				break;
	
			case PAT_DESCRIPTIONS:
	
				break;
			case PAT_CLAIMS:
	
				break;
			case PAT_ABSTRACTS:
	
				break;
			default:
				break;
		}
		return null;
	}

	private final String XLS = "xls";
	private final String XLSX = "xlsx";

	public String getExcelFileType() {
		switch (this) {
			case PATS:
				return XLS;
			case PAT_DESCRIPTIONS:
				return XLSX;
			case PAT_CLAIMS:
				return XLSX;
			case PAT_ABSTRACTS:
				return XLSX;
			default:
				return XLSX;
		}
	}

}
