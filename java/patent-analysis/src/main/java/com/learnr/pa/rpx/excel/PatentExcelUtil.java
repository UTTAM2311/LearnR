package com.learnr.pa.rpx.excel;

import static com.learnr.pa.rpx.excel.PatentExcelType.PATS;
import static com.learnr.pa.rpx.excel.PatentExcelType.PAT_ABSTRACTS;
import static com.learnr.pa.rpx.excel.PatentExcelType.PAT_CLAIMS;
import static com.learnr.pa.rpx.excel.PatentExcelType.PAT_DESCRIPTIONS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.learnr.pa.beans.PatentBean;

public class PatentExcelUtil {

	private static final Logger logger = LoggerFactory.getLogger(PatentExcelUtil.class);

	private static Map<PatentExcelType, String> fileNameMap;
	private static String SAMPLE_FILE_PATH = "sample-files-rpx/";
	/* Static Initialization */

	static {
		fileNameMap = new HashMap<PatentExcelType, String>();
		fileNameMap.put(PATS, SAMPLE_FILE_PATH + "pats.xls");
		fileNameMap.put(PAT_ABSTRACTS, SAMPLE_FILE_PATH + "pat_abstracts.xlsx");
		fileNameMap.put(PAT_CLAIMS, SAMPLE_FILE_PATH + "pat_claims.xlsx");
		fileNameMap.put(PAT_DESCRIPTIONS, SAMPLE_FILE_PATH + "pat_descriptions.xlsx");
	}

	/* Methods */

	public static List<PatentBean> readPatsExcel(PatentExcelType type) {
		File excelFile = getPatentResourseFile(type);

		try {
			FileInputStream file = new FileInputStream(excelFile);

			// Get the workbook instance for XLS file
			Workbook workbook = getWorkBook(file, type);

			// Get first sheet from the workbook
			Sheet sheet = workbook.getSheetAt(0);

			// Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "\t\t");
						break;
					}
				}
				System.out.println("");
			}
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Workbook getWorkBook(FileInputStream file, PatentExcelType type) {
		if (type == null || file == null)
			return null;

		try {
			if (type.getExcelFileType().equals("xls")) {
				return new HSSFWorkbook(file);
			} else {
				return new XSSFWorkbook(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static File getPatentResourseFile(PatentExcelType type) {
		Resource fileRes = new ClassPathResource(fileNameMap.get(type));
		if (fileRes != null) {
			try {
				return fileRes.getFile();
			} catch (IOException e) {
				logger.error("Unable to find the resource file for type : " + type);
				e.printStackTrace();
			}
		}

		return null;
	}

}
