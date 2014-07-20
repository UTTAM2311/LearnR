package com.learnr.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.learnr.core.util.Verify;
import com.learnr.core.util.ds.LabeledMatrix;
import com.learnr.core.util.ds.LabeledRealMatrix;

/**
 * Basic concrete implementation of {@link DataSet} with the Generic data type as {@link Double}.
 */
public final class RealDataSet<RL, CL> implements DataSet<Double, RL, CL> {

	private List<DataPoint<Double, CL>> points = new ArrayList<>();
	
	private final LabeledRealMatrix<RL, CL> lMatrix;
	private Map<String, Object> metadata = new HashMap<>();
	
	private final Integer dimension;
	private List<CL> colLabels = new ArrayList<>();
	private List<RL> rowLabels = new ArrayList<>();
	
	/* Constructors */

	public RealDataSet(List<DataPoint<Double, CL>> points) {
		super();
		Verify.notEmpty(points);
		
		this.points = points;
		
		DataPoint<Double, CL> point = points.get(0);
		if(point != null) {
			this.dimension = point.dimension();
			this.colLabels = point.asVector().getLabels();
		} else {
			this.dimension = 0;
		}
		
		double[][] d = RealDataUtils.as2DArray(points);
		lMatrix = new LabeledRealMatrix<RL, CL>(rowLabels, colLabels, d);
	}
	
	public RealDataSet(List<RL> rowLabels, List<DataPoint<Double, CL>> points) {
		this(points);
		Verify.notEmpty(points);
		Verify.notEmpty(rowLabels);
		Verify.isTrue(rowLabels.size() == points.size());
		
		this.rowLabels = rowLabels;
	}

	/* Implementations */

	@Override
	public Integer dimension() {
		return dimension;
	}

	@Override
	public Integer size() {
		return this.getDataPoints().size();
	}

	@Override
	public List<CL> getColumnLables() {
		return Collections.unmodifiableList(colLabels);
	}

	@Override
	public List<RL> getRowLables() {
		return Collections.unmodifiableList(rowLabels);
	}

	@Override
	public List<DataPoint<Double, CL>> getDataPoints() {
		return Collections.unmodifiableList(points);
	}

	@Override
	public LabeledMatrix<RL, CL> asMatrix() {
		return lMatrix;
	}

	@Override
	public Map<String, Object> getMetadata() {
		if(metadata == null)
			metadata = new HashMap<>();
		
		return Collections.unmodifiableMap(metadata);
	}


}
