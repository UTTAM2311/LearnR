package com.learnr.core.classification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;



public class GeneratingMatrixfromMaps {
	/**
	 * Converts list of mappings into matrix where each map is a row.
	 * 
	 * @param maps
	 * @return
	 */
	public static RealMatrix MapToMatrix(List<Map<String, Integer>> maps) {
		RealMatrix matrix = new Array2DRowRealMatrix(maps.size(), maps.get(0).size());
		List<Integer> values = new ArrayList<Integer>();
		Map<String, Integer> map = new TreeMap<String, Integer>();
		System.out.println("Size of the maps :" + maps.size());
		for (int i = 0; i < maps.size(); i++) {
			map = maps.get(i);
			System.out.println(map);
			values.addAll(map.values());
			for (int j = 0; j < map.size(); j++) {
				matrix.setEntry(i, j, values.get(j));
				System.out.println("Matrix entry[" + i + "][" + j + "]");
				System.out.println(matrix.getEntry(i, j));
			}
			values.clear();
		}
		return matrix;
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	public static List<Map<String, Integer>> creatingMapPoints(Map<String, Integer> map) {
		List<Map<String, Integer>> maps = new ArrayList<Map<String, Integer>>();
		maps.add(map);
		return maps;
	}

}
