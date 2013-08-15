package com.imaginea.dc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.CharReader;
import org.apache.lucene.analysis.charfilter.HTMLStripCharFilter;

public class TextProcessingUtil {
	
	public static String stripHtml(String inHtmlText) throws IOException{
	
		StringBuilder out = new StringBuilder();
		StringReader strReader = new StringReader(inHtmlText);
		try {
			HTMLStripCharFilter html = new HTMLStripCharFilter(
					CharReader.get(strReader.markSupported() ? strReader
							: new BufferedReader(strReader)));
			char[] cbuf = new char[1024 * 10];
			while (true) {
				int count = html.read(cbuf);
				if (count == -1)
					break; // end of stream mark is -1
				if (count > 0)
					out.append(cbuf, 0, count);
			}
			html.close();
		} catch (IOException e) {
			throw e;
		}
	
		return out.toString();
		
	}
	
}
