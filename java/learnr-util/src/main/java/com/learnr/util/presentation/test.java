package com.learnr.util.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String path = "/home/manojkumar/Desktop/birch2.txt";
		List<String> li = new ArrayList<String>();
		Txtreader txt = new Txtreader(path);
		li = txt.openFile();
		System.out.println(li.size());
		System.out.println(li.get(1));
		String delims = "[ ]+";
		String[] a = li.get(1).split(delims);
		for(int i=0;i<a.length;i++)
		{
			System.out.println(a[i]);
		}
		System.out.println(a.length);
		System.out.println("a[0] :"+a[0]);
		System.out.println(Double.parseDouble(a[1]));
	}

	
}
