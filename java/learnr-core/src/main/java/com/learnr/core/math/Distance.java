package com.learnr.core.math;

import org.apache.commons.math3.ml.distance.CanberraDistance;
import org.apache.commons.math3.ml.distance.ChebyshevDistance;
import org.apache.commons.math3.ml.distance.EarthMoversDistance;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.ml.distance.ManhattanDistance;

public class Distance {

	public static double euclideanDistance(double[] p1, double[] p2) {
		EuclideanDistance calc = new EuclideanDistance();
		return calc.compute(p1, p1);
	}

	public static double manhattanDistance(double[] p1, double[] p2) {
		ManhattanDistance calc = new ManhattanDistance();
		return calc.compute(p1, p1);
	}

	public static double canberraDistance(double[] p1, double[] p2) {
		CanberraDistance calc = new CanberraDistance();
		return calc.compute(p1, p1);
	}

	public static double chebyshevDistance(double[] p1, double[] p2) {
		ChebyshevDistance calc = new ChebyshevDistance();
		return calc.compute(p1, p1);
	}

	public static double earthMoversDistanc(double[] p1, double[] p2) {
		EarthMoversDistance calc = new EarthMoversDistance();
		return calc.compute(p1, p1);
	}
}
