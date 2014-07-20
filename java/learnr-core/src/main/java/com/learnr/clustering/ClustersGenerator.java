package com.learnr.clustering;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.clustering.MultiKMeansPlusPlusClusterer;

//Generates clusters from the given data
public class ClustersGenerator {
	List<Cluster<DataPoint>> clusterListOfDBSCANDependentAlgo = new ArrayList<Cluster<DataPoint>>();
	List<CentroidCluster<DataPoint>> clusterListOfKDependentAlgos = new ArrayList<CentroidCluster<DataPoint>>();
	static public List<CentroidCluster<DataPoint>> kMeanCluster = new ArrayList<CentroidCluster<DataPoint>>();
	static public List<CentroidCluster<DataPoint>> fuzzyKmeanCluster = new ArrayList<CentroidCluster<DataPoint>>();
	static public List<Cluster<DataPoint>> dbScanCluster = new ArrayList<Cluster<DataPoint>>();
	static public List<CentroidCluster<DataPoint>> multiKmeanCluster = new ArrayList<CentroidCluster<DataPoint>>();
	static public List<DataPoint> points = new ArrayList<DataPoint>();
	public int dimension;
	public static int size;

	/**
	 * calculate the no of rows in the input matrix
	 * 
	 * @param data
	 *            input as RealMatrix
	 */
	public ClustersGenerator(RealMatrix data) {
		if (data == null) {
			throw new IllegalArgumentException("data is null");
		}

		size = data.getRowDimension();
		readingDataFromMatrix(data);
	}

	/**
	 * calculate the no of column in the input matrix and create datapoints
	 * 
	 * @param x
	 *            as RealMatrix Reads RealMatrix x and create a list containing points
	 */
	public void readingDataFromMatrix(RealMatrix x) {
		dimension = x.getColumnDimension();
		for (int i = 0; i < size; i++) {
			double[] row = x.getRow(i);

			DataPoint point = new DataPoint(row);
			points.add(point);
		}
	}

	/**
	 * 
	 * @param noOfClusters
	 *            as input
	 * @return List of clusters
	 */
	public List<CentroidCluster<DataPoint>> generaterKMeansPlusPlusClusters(int noOfClusters) {
		if (noOfClusters == 0) {
			throw new IllegalArgumentException("Number of Clusters is zero, Clustering is not done");
		}
		KMeansPlusPlusClusterer<DataPoint> clusterer = new KMeansPlusPlusClusterer<DataPoint>(noOfClusters);
		kMeanCluster = clusterer.cluster(points);
		displayingClustersAndStoringInList dcactm = new displayingClustersAndStoringInList();
		clusterListOfKDependentAlgos = dcactm.displayingAndStoring(kMeanCluster, noOfClusters);
		return clusterListOfKDependentAlgos;
	}

	/**
	 * 
	 * @param noOfClusters
	 *            as input
	 * @return List of Clusters
	 */
	public List<CentroidCluster<DataPoint>> generaterMultipleKMeansPlusPlusClusters(int noOfClusters) {
		if (noOfClusters == 0) {
			throw new IllegalArgumentException("Number of Clusters is zero, Clustering is not done");
		}
		KMeansPlusPlusClusterer<DataPoint> clusterer = new KMeansPlusPlusClusterer<DataPoint>(noOfClusters);
		MultiKMeansPlusPlusClusterer<DataPoint> mkmeans = new MultiKMeansPlusPlusClusterer<DataPoint>(clusterer, 5);
		multiKmeanCluster = mkmeans.cluster(points);
		displayingClustersAndStoringInList dcactm = new displayingClustersAndStoringInList();
		clusterListOfKDependentAlgos = dcactm.displayingAndStoring(multiKmeanCluster, noOfClusters);
		return clusterListOfKDependentAlgos;
	}

	/**
	 * 
	 * @param noOfClusters
	 *            as input
	 * @param fuzzinessfactor
	 *            as input
	 * @return List of clusters
	 */
	public List<CentroidCluster<DataPoint>> generateFuzzyKMeansClusters(int noOfClusters, double fuzzinessfactor) {
		if (noOfClusters == 0) {
			throw new IllegalArgumentException("Number of Clusters is zero, Clustering is not done");
		}
		FuzzyKMeansClusterer<DataPoint> clusterer = new FuzzyKMeansClusterer<DataPoint>(noOfClusters, fuzzinessfactor);
		fuzzyKmeanCluster = clusterer.cluster(points);
		displayingClustersAndStoringInList dcactm = new displayingClustersAndStoringInList();
		clusterListOfKDependentAlgos = dcactm.displayingAndStoring(fuzzyKmeanCluster, noOfClusters);
		return clusterListOfKDependentAlgos;
	}

	/**
	 * 
	 * @param neighbourhoodRadius
	 *            as input
	 * @param noOfNeighbouroodPoints
	 *            as input
	 * @return List of clusters
	 */
	public List<Cluster<DataPoint>> DBSCAN(double neighbourhoodRadius, int noOfNeighbouroodPoints) {

		DBSCANClusterer<DataPoint> clusterer = new DBSCANClusterer<DataPoint>(neighbourhoodRadius,
				noOfNeighbouroodPoints);
		dbScanCluster = clusterer.cluster(points);
		displayingClustersAndStoringInList dcactm = new displayingClustersAndStoringInList();
		clusterListOfDBSCANDependentAlgo = dcactm.displayingAndConvetinForDBSCAN(dbScanCluster);
		return clusterListOfDBSCANDependentAlgo;
	}
}
