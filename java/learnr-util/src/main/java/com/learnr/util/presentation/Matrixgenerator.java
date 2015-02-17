package com.learnr.util.presentation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class Matrixgenerator {
	final List<String> list;
	public Matrixgenerator(List<String> list_of_strings){
		list = list_of_strings;
	}
	public RealMatrix constructMatrix(){
		String delims = "[ ]+";
		String[] aaa = list.get(1).split(delims);
		double[][] matrixdata = new double[list.size()][aaa.length-1];
		for(int i=0;i<list.size();i++)
		{
			String[] a = list.get(i).split(delims);
			for(int j=1;j<aaa.length;j++)
			{
				matrixdata[i][j-1] = Double.parseDouble(a[j]); 
			}
			
		}
		RealMatrix matrix = new Array2DRowRealMatrix(matrixdata);
		return matrix;
	}
	
	
	
}
