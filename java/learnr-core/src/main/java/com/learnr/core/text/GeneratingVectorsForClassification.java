package com.learnr.core.text;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GeneratingVectorsForClassification {

	private List<ArrayList<String>> listOfMapKeysToListOfStrings(List<Map<String, Integer>> listOfMaps) {
		List<ArrayList<String>> listOfStrings = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < listOfMaps.size(); i++) {
			ArrayList<String> keys = new ArrayList<String>(listOfMaps.get(i).keySet());
			listOfStrings.add(keys);
		}
		return listOfStrings;
	}

	private List<ArrayList<Integer>> listOfMapKeysToListOfIntegers(List<Map<String, Integer>> listOfMaps) {
		List<ArrayList<Integer>> listOfIntegers = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < listOfMaps.size(); i++) {
			ArrayList<Integer> values = new ArrayList<Integer>(listOfMaps.get(i).values());
			listOfIntegers.add(values);
		}
		return listOfIntegers;
	}

	public List<Map<String, Integer>> dimensionVector1(List<Map<String, Integer>> listOfMaps) {
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		List<ArrayList<String>> listOfStrings = new ArrayList<ArrayList<String>>();
		List<ArrayList<Integer>> listOfIntegers = new ArrayList<ArrayList<Integer>>();
		listOfStrings = listOfMapKeysToListOfStrings(listOfMaps);
		listOfIntegers = listOfMapKeysToListOfIntegers(listOfMaps);
		int count;
		for (int i = 0; i < listOfStrings.size() - 1; i++) {
			for (int j = 0; j < listOfStrings.get(i).size(); j++) {
				count = 0;
				for (int k = 0; k < listOfStrings.get(i + 1).size(); k++) {
					if ((listOfStrings.get(i).get(j).equals(listOfStrings.get(i + 1).get(k))) && (k != j)) {
						listOfStrings.get(i + 1).remove(k);
						listOfStrings.get(i + 1).add(j, listOfStrings.get(i).get(j));
						int value = listOfIntegers.get(i + 1).remove(k);
						listOfIntegers.get(i + 1).add(j, value);
						break;
					} else {
						count = count + 1;
					}
				}
				if (count == listOfStrings.get(i + 1).size()) {
					listOfStrings.get(i + 1).add(j, listOfStrings.get(i).get(j));
					listOfIntegers.get(i + 1).add(j, 0);
				}
			}
		}

		int size = listOfStrings.size();
		for (int x = 0; x < listOfStrings.size() - 1; x++) {
			for (int p = listOfStrings.get(x).size(); p < listOfStrings.get(size - 1).size(); p++) {
				listOfStrings.get(x).add(listOfStrings.get(size - 1).get(p));
				listOfIntegers.get(x).add(0);
			}
		}
		for (int i = 0; i < listOfStrings.size(); i++) {
			Map<String, Integer> map = new LinkedHashMap<String, Integer>();
			for (int j = 0; j < listOfStrings.get(i).size(); j++) {
				map.put(listOfStrings.get(i).get(j), listOfIntegers.get(i).get(j));
			}
			list.add(map);
		}
		return list;
	}
}
