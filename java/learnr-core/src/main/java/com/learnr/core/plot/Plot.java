package com.learnr.core.plot;

import javax.swing.JFrame;

import org.apache.commons.math3.linear.RealMatrix;
import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;
import org.math.plot.PlotPanel;

import com.learnr.core.util.Verify;

public class Plot {

	public Plot2DPanel plot2dPanel;
	public Plot3DPanel plot3dPanel;
	private JFrame frame;

	private RealMatrix data = null;
	private double[] xData = null;
	private double[] yData = null;
	private double[] zData = null;

	private final int dimension;

	/* --- Constructors --- */

	/**
	 * Default Constructor
	 */
	public Plot() {
		super();

		// Initiate plots
		this.plot2dPanel = new Plot2DPanel();
		this.plot3dPanel = new Plot3DPanel();
		this.dimension = 0;
	}

	/**
	 * Constructor using {@code RealMatrix as data} as parameter
	 * 
	 * @param data
	 *            data as {@link RealMatrix} to plot
	 */
	public Plot(RealMatrix data) {
		super();

		Verify.notNull(data);
		this.data = data;
		this.dimension = data.getColumnDimension();

		// Handle data
		if (this.dimension > 0)
			this.xData = data.getColumn(0);

		if (this.dimension > 1)
			this.yData = data.getColumn(1);

		if (this.dimension > 2)
			this.zData = data.getColumn(2);

		// Initiate plots
		this.plot2dPanel = new Plot2DPanel();
		this.plot3dPanel = new Plot3DPanel();
	}

	/* --- Plotting Utilities --- */
	
	public void clear2Dplot() {
		plot2dPanel = new Plot2DPanel();
		attachPlotPanelToFrame(plot2dPanel, " ");
	}

	public void clear3Dplot() {
		plot3dPanel = new Plot3DPanel();
		attachPlotPanelToFrame(plot3dPanel, " ");
	}

	// Scattered Plots

	public void plotScatteredIn2D(String plotName) {
		if (xData == null || yData == null)
			throw new PlotException("Insufficient Data");

		plot2dPanel.addScatterPlot(plotName, xData, yData);
		attachPlotPanelToFrame(plot2dPanel, plotName);
	}

	public void plotScatteredIn2D(String plotName, int xColIndex, int yColIndex) {
		if (data == null)
			throw new PlotException("data matrix is null");

		plot2dPanel.addScatterPlot(plotName, data.getColumn(xColIndex), data.getColumn(yColIndex));
		attachPlotPanelToFrame(plot2dPanel, plotName);
	}

	public void plotScatteredIn3D(String plotName) {
		if (xData == null || yData == null || zData == null)
			throw new PlotException("Insufficient Data");

		plot3dPanel.addScatterPlot(plotName, xData, yData, zData);
		attachPlotPanelToFrame(plot3dPanel, plotName);
	}

	public void plotScatteredIn3D(String plotName, int xColIndex, int yColIndex, int zColIndex) {
		if (data == null)
			throw new PlotException("data matrix is null");

		plot3dPanel.addScatterPlot(plotName, data.getColumn(xColIndex), data.getColumn(yColIndex),
				data.getColumn(zColIndex));
		attachPlotPanelToFrame(plot3dPanel, plotName);
	}
	
	// Line Plots
	
	public void plotConnectedIn2D(String plotName) {
		if (xData == null || yData == null)
			throw new PlotException("Insufficient Data");

		plot2dPanel.addLinePlot(plotName, xData, yData);
		attachPlotPanelToFrame(plot2dPanel, plotName);
	}

	public void plotConnectedIn2D(String plotName, int xColIndex, int yColIndex) {
		if (data == null)
			throw new PlotException("data matrix is null");

		plot2dPanel.addLinePlot(plotName, data.getColumn(xColIndex), data.getColumn(yColIndex));
		attachPlotPanelToFrame(plot2dPanel, plotName);
	}
	
	/* --- Private Methods --- */

	private void attachPlotPanelToFrame(PlotPanel plotPanel, String frameName) {
		if (frameName == null || frameName.isEmpty())
			frameName = plotPanel.getName();

		frame = new JFrame(frameName);
		frame.setContentPane(plotPanel);
		frame.setVisible(true);
	}

	/* --- Getters and Setters --- */

	public RealMatrix getData() {
		return data;
	}

	public void setData(RealMatrix data) {
		this.data = data;
	}

}
