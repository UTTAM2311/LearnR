package com.learnr.core.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

public class StopWordUtilTest {
	@Test
	public void teststopword() {
		Map<String , Integer> map1 = new HashMap<String,Integer>(); 
		Map<String , Integer> map2 = new HashMap<String,Integer>(); 
		List<Map> list = new ArrayList<Map>();
		List<String> stopwords = new ArrayList<String>();
		map1.put("a", 1);
		map1.put("b", 2);
		map1.put("c", 2);
		map2.put("d", 2);
		map2.put("e", 1);
		map2.put("a", 3);
		list.add(map1);
		list.add(map2);
		 StopWordUtil sw = new  StopWordUtil();
		 stopwords= sw.StopWords(list);
		Assert.assertEquals(true, stopwords.contains("a"));
	}
	@Test
	public void getwordcounttest(){
		List<String> list = new ArrayList<String>();
		Map<String , Integer> map = new HashMap<String,Integer>(); 
		list.add("a");
		list.add("c");
		list.add("a");
		list.add("b");
		 StopWordUtil sw = new  StopWordUtil();
		 map = sw.getWordCount(list);
		 int a = map.get("a");
		 Assert.assertEquals(2, a);
	}
	@Test
	public void getfinalmaptest(){
		Map<String , Integer> map1 = new HashMap<String,Integer>(); 
		Map<String , Integer> map2 = new HashMap<String,Integer>(); 
		Map<String , Integer> finalmap = new HashMap<String,Integer>(); 
		List<Map> list = new ArrayList<Map>();
		List<String> stopwords = new ArrayList<String>();
		map1.put("a", 1);
		map1.put("b", 2);
		map1.put("c", 2);
		map2.put("d", 2);
		map2.put("e", 1);
		map2.put("a", 3);
		list.add(map1);
		list.add(map2);
		stopwords.add("a");
		StopWordUtil sw = new  StopWordUtil();
		finalmap = sw.getMap(list, stopwords);
		Assert.assertEquals(4, finalmap.size());
	}
	@Test
	public void maptolisttest(){
		Map<String , Integer> map2 = new HashMap<String,Integer>();
		List<String> list = new ArrayList<String>();
		map2.put("d", 2);
		map2.put("e", 1);
		map2.put("a", 3);
		StopWordUtil sw = new  StopWordUtil();
		list = sw.mapTolist(map2);
		Assert.assertEquals(6, list.size());
		
	}
	@Test
	public void  dimensionvectortest(){
		List<Map> list = new ArrayList<Map>();
		List<Map> finallist = new ArrayList<Map>();
		Map<String, Integer> map1 = new TreeMap<String, Integer>();
		Map<String, Integer> map2 = new TreeMap<String, Integer>();
		map1.put("a",1);
		map1.put("b",1);
		map1.put("c",1);
		map2.put("a",2);
		map2.put("e",1);
		list.add(map1);
		list.add(map2);
		StopWordUtil st = new StopWordUtil();
		finallist=st.dimensionVector(list);
		Assert.assertEquals(4, finallist.get(0).size());
		Assert.assertEquals(4, finallist.get(1).size());
		Assert.assertEquals(0, finallist.get(0).get("e"));
		Assert.assertEquals(0, finallist.get(1).get("b"));
		Assert.assertEquals(0, finallist.get(1).get("c"));
	}
}
