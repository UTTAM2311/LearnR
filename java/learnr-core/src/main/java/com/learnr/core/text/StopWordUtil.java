package com.learnr.core.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StopWordUtil {
	// method for finding stop words
	public List<String> StopWords(List<Map> listOfMaps) {
		int size, k = 0;
		List<String> stopwords = new ArrayList<String>();
		Map<String, Integer> totalMap = new HashMap<String, Integer>();
		size = listOfMaps.size();
		for (int i = 0; i < size; i++) {
			totalMap.putAll(listOfMaps.get(i));
		}
		List<String> listOfKeysetsOfTotalMap = new ArrayList<String>(totalMap.keySet());
		Collections.sort(listOfKeysetsOfTotalMap);
		for (int i = 0; i < size - 1; i++) {
			if (listOfMaps.get(i).size() > listOfMaps.get(i + 1).size()) {
				k = i + 1;
			} else {
				k = i;
			}
		}
		List<String> listOfkeysetsOfListOfMaps = new ArrayList<String>(listOfMaps.get(k).keySet());
		int size2;
		size2 = listOfkeysetsOfListOfMaps.size();
		for (int i = 0; i < size2; i++) {
			for (int j = 0; j < size2; j++) {
				if (listOfKeysetsOfTotalMap.get(i).equals(listOfkeysetsOfListOfMaps.get(j))) {
					stopwords.add(listOfKeysetsOfTotalMap.get(i));
				}
			}
		}
		return stopwords;
	}
// method for converting a Listofstrings to a map
	public Map<String, Integer> getWordCount(List<String> inStrs) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int size = inStrs.size();
		String covertionToLowerCase;
		int count = 0, k = 0;
		for (int i = 0; i < size; i++) {
			covertionToLowerCase = inStrs.get(i).toLowerCase();
			inStrs.remove(i);
			inStrs.add(i, covertionToLowerCase);
		}
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
	public static Map<String, Integer> getMap(List<Map> listOfMaps) {
		int size, k = 0;
		Map<String, Integer> totalMap = new HashMap<String, Integer>();
		size = listOfMaps.size();
		for (int i = 0; i < size; i++) {
			totalMap.putAll(listOfMaps.get(i));
		}
		List<String> listOfKeysetsOfTotalMap = new ArrayList<String>(totalMap.keySet());
		Collections.sort(listOfKeysetsOfTotalMap);
		for (int i = 0; i < size - 1; i++) {
			if (listOfMaps.get(i).size() > listOfMaps.get(i + 1).size()) {
				k = i + 1;
			} else {
				k = i;
			}
		}
		List<String> listOfkeysetsOfListOfMaps = new ArrayList<String>(listOfMaps.get(k).keySet());
		int size2;
		String key;
		size2 = listOfkeysetsOfListOfMaps.size();
		for (int i = 0; i < size2; i++) {
			for (int j = 0; j < size2; j++) {
				if (listOfKeysetsOfTotalMap.get(i).equals(listOfkeysetsOfListOfMaps.get(j))) {
					key = listOfKeysetsOfTotalMap.get(i);
					totalMap.remove(key);
				}
			}
		}
		return totalMap;
	}
}
