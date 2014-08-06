package com.learnr.core.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;

import com.learnr.util.Verify;

public class LabeledRealMatrix<RL, CL> extends Array2DRowRealMatrix implements LabeledMatrix<RL, CL> {

	private static final long serialVersionUID = 1L;

	private final List<RL> rowLabels;
	private final List<CL> columnLabels;

	/* --- Constructors --- */

	public LabeledRealMatrix(double[][] d) throws DimensionMismatchException, NoDataException, NullArgumentException {
		super(d);

		// Labels
		this.rowLabels = new ArrayList<RL>();
		this.columnLabels = new ArrayList<CL>();
	}

	public LabeledRealMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
		super(rowDimension, columnDimension);

		// Labels
		this.rowLabels = new ArrayList<RL>();
		this.columnLabels = new ArrayList<CL>();
	}

	public LabeledRealMatrix(final List<RL> rowLabels, final List<CL> columnLabels) throws NullPointerException {
		super(rowLabels.size(), columnLabels.size());

		// Labels
		this.rowLabels = rowLabels;
		this.columnLabels = columnLabels;
	}

	public LabeledRealMatrix(final List<RL> rowLabels, final List<CL> columnLabels, double[][] d)
			throws NullPointerException, DimensionMismatchException {

		super(d);

		// Sanity Checks
		Verify.notNull(rowLabels);
		Verify.notNull(columnLabels);

		if (rowLabels.size() != getRowDimension())
			throw new DimensionMismatchException(rowLabels.size(), getRowDimension());

		if (columnLabels.size() != getColumnDimension())
			throw new DimensionMismatchException(columnLabels.size(), getColumnDimension());

		// Initialize Labels
		this.rowLabels = rowLabels;
		this.columnLabels = columnLabels;
	}

	/* --- Implementations --- */

	@Override
	public List<RL> getRowLabels() {
		return Collections.unmodifiableList(rowLabels);
	}

	@Override
	public List<CL> getColumnLabels() {
		return Collections.unmodifiableList(columnLabels);
	}

	@Override
	public RL getRowLabel(int row) throws OutOfRangeException {
		MatrixUtils.checkRowIndex(this, row);
		return getRowLabels().get(row);
	}

	@Override
	public CL getColumnLabel(int column) throws OutOfRangeException {
		MatrixUtils.checkColumnIndex(this, column);
		return getColumnLabels().get(column);
	}

	@Override
	public int getRowIndex(RL rowLabel) throws LabelDoesNotExistException {
		Verify.notNull(rowLabel);

		int index = rowLabels.indexOf(rowLabel);
		if (index == -1)
			throw new LabelDoesNotExistException();

		return index;
	}

	@Override
	public int getColumnIndex(CL columnLabel) throws LabelDoesNotExistException {
		Verify.notNull(columnLabel);

		int index = columnLabels.indexOf(columnLabel);
		if (index == -1)
			throw new LabelDoesNotExistException();

		return index;
	}

	@Override
	public LabeledRealVector<RL> getRowVector(RL rowLabel) throws LabelDoesNotExistException {
		Verify.notNull(rowLabel);
		return new LabeledRealVector<RL>(getRow(rowLabel), new ArrayList<RL>(rowLabels));
	}

	@Override
	public LabeledRealVector<CL> getColumnVector(CL columnLabel) throws LabelDoesNotExistException {
		Verify.notNull(columnLabel);
		return new LabeledRealVector<CL>(getColumn(columnLabel), new ArrayList<CL>(columnLabels));
	}

	@Override
	public double[] getRow(RL rowLabel) throws LabelDoesNotExistException {
		Verify.notNull(rowLabel);
		return getRow(getRowIndex(rowLabel));
	}

	@Override
	public double[] getColumn(CL columnLabel) throws LabelDoesNotExistException {
		Verify.notNull(columnLabel);
		return getColumn(getColumnIndex(columnLabel));
	}

}
