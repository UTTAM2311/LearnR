package com.learnr.util.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.linear.ArrayRealVector;

import com.learnr.util.Verify;

public class LabeledRealVector<L> extends ArrayRealVector {

	private static final long serialVersionUID = 1L;
	
	private final List<L> labels;

	/* --- Constructors  --- */

	public LabeledRealVector(double[] d) {
		super(d);
		
		// labels
		this.labels = new ArrayList<L>();
	}

	public LabeledRealVector(double[] d, List<L> labels) {
		super(d);
		
		// Sanity checks
		Verify.notNull(labels);
		if (d.length != labels.size())
			throw new DimensionMismatchException(d.length, labels.size());
		
		// labels
		this.labels = labels;
	}
	
	/* --- Methods --- */
	
	
	public double getEntry(L label) throws OutOfRangeException {
		Verify.notNull(label);
        int index = labels.indexOf(label);
        return getEntry(index);
    }
	
	public List<L> getLabels(){
        return Collections.unmodifiableList(labels);
    }

}
