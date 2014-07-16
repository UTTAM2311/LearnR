package com.learnr.plot;

import javax.swing.JFrame;

import org.apache.commons.math3.linear.RealMatrix;
import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;

public class Plot {
	private static Plot2DPanel plot2D;
	private static Plot3DPanel plot3D;
	private static double[] xData;
	private static double[] yData;
	private static double[] zData;

/**
 *  2Dplot for matrix with xcolumnindex as x axis and ycolumnindex as y axis
 * @param data 
 *  	 matrix containing data points
 * @param xcolumnindex
 *     indicate x-axis
 * @param ycolumnindex
 *     indicate y-axis
 * @param plotName
 *         writes the name on the plot 
 */
	public void for2DimensionPlot(RealMatrix data, int xcolumnindex, int ycolumnindex, String plotName) {
		plot2D=new Plot2DPanel();
		xData = data.getColumn(xcolumnindex);
		yData = data.getColumn(ycolumnindex);
		Plot.Plot2D("Plot2D", xData, yData, plotName);
		Plot.frame(plot2D, plotName);
	}

	/**
	 *  @param data 
 *   	matrix containing data points
 * @param xcolumnindex
 *     indicate x-axis
 * @param ycolumnindex
 *     indicate y-axis
 *     @param zcolumnindex
 * @param plotName 
 *         writes the name on the plot 
	 *
	 * @param zcolumnindex
	 *
	 */

	
	public void for3DimensionPlot(RealMatrix data, int xcolumnindex, int ycolumnindex, int zcolumnindex, String plotName) {
		plot3D=new Plot3DPanel();
		xData = data.getColumn(xcolumnindex);
		yData = data.getColumn(ycolumnindex);
		zData = data.getColumn(zcolumnindex);
		Plot.Plot3D("Plot3D", xData, yData, zData, plotName);
		Plot.frame(plot3D, plotName);
	}


/**
 * 
 * @param data
 * 			matrix containing data points
 * @param plotName
 *  		writes the name on the plot 
 */ 
	
	public void for2DimensionPlot(RealMatrix data, String plotName) {
		plot2D=new Plot2DPanel();
		xData = data.getColumn(0);
		yData = data.getColumn(1);
		Plot.Plot2D("Plot2D", xData, yData, plotName);
		Plot.frame(plot2D, plotName);
	}

	/**
	 * 
	 * @param data
	 * 		matrix containing data points
	 * @param plotName
	 *  		writes the name on the plot 
	 */ 
	public void for3DimensionPlot(RealMatrix data, String plotName) {
		plot3D=new Plot3DPanel();
		xData = data.getColumn(0);
		yData = data.getColumn(1);
		zData = data.getColumn(2);
		Plot.Plot3D("Plot3D", xData, yData, zData,plotName);
		Plot.frame(plot3D, plotName);
	}

	private static void Plot2D(String name, double[] xData, double[] yData, String plotName) {
		plot2D.addScatterPlot(name, xData, yData);
		
	}

	private static void Plot3D(String name, double[] xData, double[] yData, double[] zData, String plotName) {
		plot3D.addScatterPlot(name, xData, yData, zData);
		
	}

	private static void frame(Plot3DPanel name, String plotName) {
		JFrame frame1 = new JFrame(plotName);
		frame1.setContentPane(name);
		frame1.setVisible(true);
	}

	private static void frame(Plot2DPanel name, String plotName) {
		JFrame frame1 = new JFrame(plotName);
		frame1.setContentPane(name);
		frame1.setVisible(true);
	}
}
