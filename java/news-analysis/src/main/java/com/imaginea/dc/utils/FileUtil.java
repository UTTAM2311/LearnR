package com.imaginea.dc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.imaginea.dc.entities.NewsArticle;

public class FileUtil {

	public static void writeURLtoFile(URL url, String filename) throws IOException {
		FileOutputStream os = new FileOutputStream(filename);
		InputStream is = url.openStream();
		byte[] buf = new byte[1048576];
		int n = is.read(buf);
		while (n != -1) {
			os.write(buf, 0, n);
			n = is.read(buf);
		}
		os.close();
	}

	public static void writeXlsFromListOfArticles(List<NewsArticle> entries,	String fileTitle) throws IOException {

		File file = new File(fileTitle);
		FileOutputStream fileOut = new FileOutputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();

		/* Default Header Row */
		Row header = sheet.createRow(0);
		Field[] fields = NewsArticle.class.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			header.createCell(i).setCellValue(fields[i].getName());
		}

		/* Article Rows */
		Row row = null;
		NewsArticle entry = null;
		for (int i = 0; i < entries.size(); i++) {
			entry = (NewsArticle) entries.get(i);
			row = sheet.createRow(i + 1);

			row.createCell(0).setCellValue((entry.getIsPositive() == null) ? "" : entry.getIsPositive().toString());
			row.createCell(1).setCellValue((entry.getSource() == null) ? "" : entry.getSource());
			row.createCell(2).setCellValue((entry.getPublisher() == null) ? "" : entry.getPublisher());
			row.createCell(3).setCellValue((entry.getAuthor() == null) ? "" : entry.getAuthor());
			row.createCell(4).setCellValue((entry.getLocation() == null) ? "" : entry.getLocation());
			row.createCell(5).setCellValue((entry.getPublishedDate() == null) ? "" : entry.getPublishedDate().toString());
			row.createCell(6).setCellValue((entry.getUpdatedDate() == null) ? "" : entry.getUpdatedDate().toString());
			row.createCell(7).setCellValue((entry.getUrl() == null) ? "" : entry.getUrl());
			row.createCell(8).setCellValue((entry.getTitle() == null) ? "" : entry.getTitle());
			row.createCell(9).setCellValue((entry.getDescription() == null) ? "" : entry.getDescription());
			row.createCell(10).setCellValue((entry.getContent() == null) ? "" : entry.getContent());

		}

		wb.write(fileOut);
		fileOut.close();

	}

}
