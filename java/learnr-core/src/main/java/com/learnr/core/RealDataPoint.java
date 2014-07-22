package com.learnr.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.ml.clustering.Clusterable;

import com.learnr.core.util.Verify;
import com.learnr.core.util.ds.LabeledRealVector;

/**
 * Basic concrete implementation of {@link DataPoint} with the Generic data type as {@link Double}.
 */
public final class RealDataPoint<L> implements DataPoint<Double, L>, Clusterable {
	
	private final LabeledRealVector<L> lVector;
	private Map<String, Object> metadata = new HashMap<>();
	
	/* Constructors */
	
	public RealDataPoint(LabeledRealVector<L> vector, Map<String, Object> metadata) {
		super();
		Verify.notNull(vector);
		
		this.lVector = vector;
		this.metadata = metadata;
	}
	
	public RealDataPoint(double[] vector, Map<String, Object> metadata) {
		super();
		Verify.notNull(vector);

		this.lVector = new LabeledRealVector<L>(vector);
		this.metadata = metadata;
	}

	public RealDataPoint(double[] vector, List<L> labels, Map<String, Object> metadata) {
		super();
		Verify.notNull(vector);
		
		this.lVector = new LabeledRealVector<L>(vector, labels);
		this.metadata = metadata;
	}

	
	/* Implementations */
	
	@Override
	public Integer dimension() {
		return lVector.getDimension();
	}

	@Override
	public LabeledRealVector<L> asVector() {
		return lVector;
	}

	@Override
	public Map<String, Object> getMetadata() {
		if(metadata == null)
			metadata = new HashMap<>();
		
		return Collections.unmodifiableMap(metadata);
	}
	
	@Override
	public double[] getPoint() {
		return lVector.toArray();
	}

	/* Other Methods */
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RealDataPoint [");
		sb.append("Dimension - " + dimension() + " : ");
		sb.append(Arrays.toString(this.getPoint()));
		sb.append("]");
		return sb.toString();
	}
	
}
