package com.learnr.core.clustering;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.clustering.MultiKMeansPlusPlusClusterer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.core.RealDataPoint;
import com.learnr.core.RealDataSet;
import com.learnr.core.util.Verify;
import com.learnr.core.util.ds.LabeledRealMatrix;

public class ClustersGenerator {

	private static final Logger logger = LoggerFactory.getLogger(ClustersGenerator.class);

	private final RealMatrix data;
	private final List<Clusterable> points;

	/* --- Constructors --- */

	public ClustersGenerator(RealMatrix data) {
		super();
		Verify.notNull(data);

		this.data = data;
		this.points = getClusterablePoints(data);
	}

	public <RL extends Object, CL extends Object> ClustersGenerator(RealDataSet<RL, CL> dataSet) {
		super();
		Verify.notNull(dataSet);

		this.data = dataSet.asMatrix();
		this.points = getClusterablePoints(data);
	}

	public <RL extends Object, CL extends Object> ClustersGenerator(LabeledRealMatrix<RL, CL> dataMatrix) {
		super();
		Verify.notNull(dataMatrix);

		this.data = dataMatrix;
		this.points = getClusterablePoints(data);
	}

	/* --- Methods --- */

	public List<CentroidCluster<Clusterable>> clusterUsingKMeansPlusPlus(int noOfClusters) {
		Verify.notNull(noOfClusters);
		Verify.isTrue(noOfClusters == 0);
		
		// Kmeans Clustering
		KMeansPlusPlusClusterer<Clusterable> kMeansClusterer = new KMeansPlusPlusClusterer<Clusterable>(noOfClusters);
		List<CentroidCluster<Clusterable>> clusters = kMeansClusterer.cluster(points);
		logger.info("Clusters from K-means : " + clusters);
		
		return clusters;
	}
	
	public List<CentroidCluster<Clusterable>> clusterUsingMultipleKMeansPlusPlus(int noOfClusters, int noOfTrials) {
		Verify.notNull(noOfClusters, noOfTrials);
		Verify.isTrue(noOfClusters == 0);
		Verify.isTrue(noOfTrials == 0);
		
		// K means Clusterer
		KMeansPlusPlusClusterer<Clusterable> kMeansClusterer = new KMeansPlusPlusClusterer<Clusterable>(noOfClusters);
		
		// Multiple Kmeans Clustering
		MultiKMeansPlusPlusClusterer<Clusterable> mkMeansClusterer = new MultiKMeansPlusPlusClusterer<Clusterable>(kMeansClusterer, noOfTrials);
		List<CentroidCluster<Clusterable>> clusters = mkMeansClusterer.cluster(points);
		logger.info("Clusters from Multi K-means (#trials - " + noOfTrials + "): " + clusters);
		
		return clusters;
	}

	public List<CentroidCluster<Clusterable>> clusterUsingFuzzyKMeans(int noOfClusters, double fuzzinessFactor) {
		Verify.notNull(noOfClusters, fuzzinessFactor);
		Verify.isTrue(noOfClusters == 0);
		Verify.isTrue(fuzzinessFactor == 0);
		
		// Fuzzy Kmeans Clustering
		FuzzyKMeansClusterer<Clusterable> fuzzyKMeansClusterer = new FuzzyKMeansClusterer<Clusterable>(noOfClusters, fuzzinessFactor);
		List<CentroidCluster<Clusterable>> clusters = fuzzyKMeansClusterer.cluster(points);
		logger.info("Clusters from Fuzzy K-means : " + clusters);
		
		return clusters;
	}

	public List<Cluster<Clusterable>> clusterUsingDBSCAN(double maxNeighbourhoodRadias, int minNeighbourhoodSize) {
		Verify.notNull(maxNeighbourhoodRadias, minNeighbourhoodSize);
		Verify.isTrue(maxNeighbourhoodRadias == 0);
		Verify.isTrue(minNeighbourhoodSize == 0);
		
		// DBSCAN Clustering
		DBSCANClusterer<Clusterable> dbscanClusterer = new DBSCANClusterer<Clusterable>(maxNeighbourhoodRadias, minNeighbourhoodSize);
		List<Cluster<Clusterable>> clusters = dbscanClusterer.cluster(points);
		logger.info("Clusters from Fuzzy K-means : " + clusters);
		
		return clusters;
	}

	/* --- Utilities --- */

	private List<Clusterable> getClusterablePoints(RealMatrix m) {
		Verify.notNull(m);
		
		List<Clusterable> points = new ArrayList<Clusterable>();
		
		RealDataPoint<String> point;
		for (int i = 0; i < m.getRowDimension(); i++) {
			double[] row = m.getRow(i);
			point = new RealDataPoint<String>(row, null);
			points.add(point);
		}
				
		return points;
	}

}
