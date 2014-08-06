package com.learnr.core.ds;

import java.util.List;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.linear.RealMatrix;

public interface LabeledMatrix<RL, CL> extends RealMatrix {

	/**
	 * Get the row Labels of the matrix.
	 * 
	 * @return List of labels
	 */
	List<RL> getRowLabels();

	/**
	 * Get the column Labels of the matrix.
	 * 
	 * @return List of labels
	 */
	List<CL> getColumnLabels();

	/**
	 * Get the row Label of the matrix with the given index.
	 * 
	 * @param row
	 *            index of the row
	 * @return row label
	 * @throws OutOfRangeException
	 *             if the specified row index is not valid
	 */
	RL getRowLabel(int row) throws OutOfRangeException;

	/**
	 * Get the column Label of the matrix with the given index.
	 * 
	 * @param column
	 *            index of the column
	 * @return column label
	 * @throws OutOfRangeException
	 *             if the specified row index is not valid
	 */
	CL getColumnLabel(int column) throws OutOfRangeException;

	/**
	 * Get the row index in the Matrix for the corresponding label .
	 * 
	 * @param rowLabel
	 *            Row label to be fetched.
	 * @return the index of the row
	 * @throws LabelDoesNotExistException
	 *             if the specified label doesn't exist
	 */
	int getRowIndex(RL rowLabel) throws LabelDoesNotExistException;

	/**
	 * Get the Column index in the Matrix for the corresponding label .
	 * 
	 * @param columnLabel
	 *            Column label to be fetched.
	 * @return the index of the column
	 * @throws LabelDoesNotExistException
	 *             if the specified label doesn't exist
	 */
	int getColumnIndex(CL columnLabel) throws LabelDoesNotExistException;

	/**
	 * Returns the entries in row label {@code row} as a vector.
	 *
	 * @param row
	 *            Row label to be fetched.
	 * @return a row vector.
	 * @throws LabelDoesNotExistException
	 *             if the specified label doesn't exist
	 */
	LabeledRealVector<RL> getRowVector(RL rowLabel) throws LabelDoesNotExistException;

	/**
	 * Get the entries at the given column label {@code column} as a vector.
	 *
	 * @param column
	 *            Column label to be fetched.
	 * @return a column vector.
	 * @throws LabelDoesNotExistException
	 *             if the specified label doesn't exist
	 */
	LabeledRealVector<CL> getColumnVector(CL columnLabel) throws LabelDoesNotExistException;

	/**
	 * Get the entries at the given row label.
	 *
	 * @param rowLabel
	 *            Row label to be fetched.
	 * @return the array of entries in the row.
	 * @throws LabelDoesNotExistException
	 *             if the specified label doesn't exist
	 */
	double[] getRow(RL rowLabel) throws LabelDoesNotExistException;

	/**
	 * Get the entries at the given column label as an array.
	 *
	 * @param columnLabel
	 *            Column label to be fetched.
	 * @return the array of entries in the column.
	 * @throws LabelDoesNotExistException
	 *             if the specified label doesn't exist
	 */
	double[] getColumn(CL columnLabel) throws LabelDoesNotExistException;

}
