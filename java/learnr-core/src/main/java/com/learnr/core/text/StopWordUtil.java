package com.learnr.core.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StopWordUtil {
	// method for finding stop words
	public List<String> StopWords(List<Map> listOfMaps) {
		if (listOfMaps.size() == 1 || listOfMaps.size() == 0) {
			return null;
		} else {
			List<String> listOfAllWords = new ArrayList<String>();
			List<String> stopwords = new ArrayList<String>();
			int size = listOfMaps.size();
			for (int i = 0; i < size; i++) {
				listOfAllWords.addAll(listOfMaps.get(i).keySet());
			}
			Collections.sort(listOfAllWords);
			int size1 = listOfAllWords.size(), b = 0, a;
			for (int i = b; i < size1; i = b) {
				a = 0;
				b++;
				for (int j = i + 1; j < size1; j++) {
					if (listOfAllWords.get(i).equals(listOfAllWords.get(j))) {
						a++;
						b++;
					}

				}
				if (a == size - 1) {
					stopwords.add(listOfAllWords.get(i));
				}
			}
			return stopwords;
		}
	}

	// method for converting a Listofstrings to a map
	public Map<String, Integer> getWordCount(List<String> inStrs) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int size = inStrs.size();
		int count = 0, k = 0;
		inStrs = covertionToLowerCase(inStrs);
		Collections.sort(inStrs);
		for (int i = k; i < size; i = k) {
			count = 1;
			k++;
			for (int j = i + 1; j < size; j++) {
				if (inStrs.get(i).equals(inStrs.get(j))) {
					count++;
					k++;
				}

			}
			map.put(inStrs.get(i), count);

		}
		return map;

	}

	// this method is for getting final word map of all the strings
	public  Map<String, Integer> getMap(List<Map> listOfMaps) {
		List<String> listOfAllWords = new ArrayList<String>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		int size = listOfMaps.size();
		for (int i = 0; i < size; i++) {
			listOfAllWords.addAll(listOfMaps.get(i).keySet());
		}
		Collections.sort(listOfAllWords);
		int  size1 = listOfAllWords.size(), a;
		for (int i = 0; i < size1-1; i++ ) {
			
			a = 0;
			for (int j = i + 1; j < size1; j++) {
				if (listOfAllWords.get(i).equals(listOfAllWords.get(j))) {
					a++;
				}
			}
			if(a == size-1)
			{
				for(int k=i;k<i+size;k++)
					listOfAllWords.remove(0);
				size1 = size1-size;
			}
		}
		map = getWordCount(listOfAllWords);
		return map;
	}
	private List<String> covertionToLowerCase(List<String> listOfAllWords)
	{
		String covertionToLowerCase;
		int size = listOfAllWords.size();
		for (int i = 0; i < size; i++) {
			covertionToLowerCase = listOfAllWords.get(i).toLowerCase();
			listOfAllWords.remove(i);
			listOfAllWords.add(i, covertionToLowerCase);
		}
		return listOfAllWords;
	}
}
