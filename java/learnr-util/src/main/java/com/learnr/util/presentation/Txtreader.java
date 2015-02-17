package com.learnr.util.presentation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Txtreader {
	final String path;

	public  Txtreader(String file_path) {
		path = file_path;
	}

	public  List<String> openFile() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		List<String> listOfLines = new ArrayList<String>();
		String line;
		while ((line = textReader.readLine()) != null) {
				listOfLines.add(line);
		}
		textReader.close();
		return listOfLines;
	}

}
