package com.learnr.core;

import java.util.List;
import java.util.Map;

import com.learnr.core.ds.LabeledMatrix;
import com.learnr.core.ds.LabeledRealVector;

/**
 * DataSet is a representation of a list of ordered {@link IDataPoint}s. This also contains the label of each dimension
 * of the data (look at {@link #getDataLables()} method). The dimension of the dataSet is same as the dimension of any
 * dataPoint in it.
 * 
 * @param <T>
 *            the data type of point data.
 * 
 * @see DataPoint
 */
public interface DataSet<T, RL, CL> {

	/**
	 * Returns the algebraic dimension of the data point in the data set.
	 * 
	 * @return the length of the array representation of the data point.
	 * 
	 * @see DataPoint#dimension()
	 */
	Integer dimension();

	/**
	 * Returns the size of the data set i.e., the no. of Data Points
	 * 
	 * @return the length of the array representation of the data point.
	 * 
	 * @see DataPoint#dimension()
	 */
	Integer size();

	/**
	 * Returns the labels of the data features/dimensions in the same order as the data stored in the dataPoint.
	 * 
	 * @return a list of labels(Strings)
	 */
	List<CL> getColumnLables();

	/**
	 * Returns the labels of the data features/dimensions in the same order as the data stored in the dataPoint.
	 * 
	 * @return a list of labels(Strings)
	 */
	List<RL> getRowLables();

	/**
	 * Returns the dataPoints of the current dataSet.
	 * 
	 * @return a list of dataPoints
	 * 
	 * @see DataPoint
	 */
	List<DataPoint<T, CL>> getDataPoints();

	/**
	 * Returns the data set as a labelled Matrix
	 * 
	 * @return a Labelled matrix
	 * @see LabeledMatrix
	 * @see LabeledRealVector
	 */
	LabeledMatrix<RL, CL> asMatrix();

	/**
	 * Any associated metadata.
	 * 
	 * @return a map of meta information
	 */
	Map<String, Object> getMetadata();

}
