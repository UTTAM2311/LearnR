package com.learnr.core.classificationtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import com.learnr.core.classification.GeneratingMatrixfromMaps;

public class mapToMatrix {
	public  void test_Matrix() {
		List<Map<String, Integer>> maps = new ArrayList<Map<String, Integer>>();
		Map<String, Integer> map = new TreeMap<String, Integer>();
		Map<String, Integer> map2 = new TreeMap<String, Integer>();
		int r = 6, a = 3;
		map.put("try", r);
		map.put("cry", a);
		map2.put("try3", a);
		map2.put("cry3", r);
		System.out.println("data");
		maps.add(map);
		maps.add(map2);
		System.out.println(maps);
		RealMatrix mat = new Array2DRowRealMatrix(2, 2);
		mat.addToEntry(0, 0, 3);
		mat.addToEntry(0, 1, 6);
		mat.addToEntry(1, 0, 6);
		mat.addToEntry(1, 1, 3);
		GeneratingMatrixfromMaps.MapToMatrix(maps);
		Assert.assertEquals(mat.getColumnMatrix(0), GeneratingMatrixfromMaps.MapToMatrix(maps).getColumnMatrix(0));
		Assert.assertEquals(mat.getColumnMatrix(1), GeneratingMatrixfromMaps.MapToMatrix(maps).getColumnMatrix(1));

	}
}
