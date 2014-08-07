package com.learnr.core.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StopWordUtil {
	/**
	 * 
	 * @param listOfMaps
	 * @return List of stopwords
	 */
	public List<String> StopWords(List<Map<String, Integer>> listOfMaps) {
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

/**
 * 
 * @param inStrs =  string
 * @return Word count in the form of a map
 */
	public Map<String, Integer> getWordCount(List<String> inStrs) {
		Map<String, Integer> map = new TreeMap<String, Integer>();
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

	/**
	 * 
	 * @param listOfMaps
	 * @param stopwords
	 * @return word count in the form of map
	 * This method is basically to give the final total(Of all the strings) count of the words
	 */
	public  Map<String, Integer> getMap(List<Map> listOfMaps,List<String> stopwords) {
		List<String> Words = new ArrayList<String>();
		List<String> listOfFinalWords = new ArrayList<String>();
		Map<String, Integer> map = new TreeMap<String, Integer>();
		int size = listOfMaps.size();
		for(int i=0;i<size;i++)
		{
			Words = mapTolist(listOfMaps.get(i));
			listOfFinalWords.addAll(Words);
		}
		Collections.sort(listOfFinalWords);
		Collections.sort(stopwords);
		int size1 = listOfFinalWords.size(),size2=stopwords.size();
		for(int i=0;i<size2;i++)
		{
			for(int j=0;j<size1;j++)
			{
				while(listOfFinalWords.get(j).equals(stopwords.get(i)))
				{
					listOfFinalWords.remove(j);
					size1 = size1-1;
				}
			}
		}
		map = getWordCount(listOfFinalWords);
		return map;
	}
	/**
	 * 
	 * @param listOfAllWords
	 * @return List of words in lower case format
	 */
	
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
	/**
	 * 
	 * @param listOfAllWords
	 * @return List of words 
	 * this method is to convert map to a list which containing a same word as many times as key value in map
	 */
	
	public  List<String> mapTolist(Map<String,Integer> map){
		List<String> list = new ArrayList<String>(map.keySet());
		List<Integer> integer = new ArrayList<Integer>(map.values());
		List<String> finalList = new ArrayList<String>();
		int size = list.size(),k;
		for(int i=0;i<size;i++)
		{
			k = integer.get(i);
			for(int j=0;j<k;j++)
			{
				finalList.add(list.get(i));
			}
		}
		
		return finalList;
	}
	/**
	 * converts every map to have same dimensions
	 * @param listOfMaps
	 * @return listOfMaps
	 */
	public List<Map<String, Integer>> dimensionVector(List<Map<String, Integer>> listOfMaps){
		int size = listOfMaps.size();
		Map<String,Integer> bigMap = new TreeMap<String,Integer>();
		for(int i=0;i<size;i++)
		{
			bigMap.putAll(listOfMaps.get(i));
		}
		List<String> biglist = new ArrayList<String>(bigMap.keySet());
		List<String> smalllist = new ArrayList<String>();
		int count;
		for(int i=0;i<listOfMaps.size();i++)
		{
			smalllist.addAll(listOfMaps.get(i).keySet());
			for(int j=0;j<biglist.size();j++)
			{
				count =0;
				for(int k=0;k<smalllist.size();k++)
				{
					if(smalllist.get(k).equals(biglist.get(j)))
					{
						break;
					}
					else{
						count = count+1;
					}
				}
				if(count==smalllist.size())
				{
					listOfMaps.get(i).put(biglist.get(j), 0);
				}
			}
			smalllist.clear();
		}
		return listOfMaps;
	}
}
