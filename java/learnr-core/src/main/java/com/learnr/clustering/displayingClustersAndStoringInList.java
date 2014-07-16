package com.learnr.clustering;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;

public class displayingClustersAndStoringInList {

	double[] x;
	static int dimension;
	List<CentroidCluster<DataPoint>> clusterListOfKDependentAlgos = new ArrayList<CentroidCluster<DataPoint>>();
	List<Cluster<DataPoint>> clusterListOfDBSCANDependentAlgo = new ArrayList<Cluster<DataPoint>>();

	/**
	 * 
	 * @param clusters
	 *            as input
	 * @param noOfClusters
	 *            in input
	 * @return List of clusters
	 */
	public List<CentroidCluster<DataPoint>> displayingAndStoring(List<CentroidCluster<DataPoint>> clusters,
			int noOfClusters) {
		for (int i = 0; i < noOfClusters; i++) {
			CentroidCluster<DataPoint> cluster = clusters.get(i);
			List<DataPoint> cPoints = cluster.getPoints();
			x = cPoints.get(0).getPoint();
			dimension = x.length;
			System.out.println(dimension);
			System.out.println("Cluster " + (i + 1) + " data : " + cPoints);
			Clusterable cCenter = cluster.getCenter();
			System.out.println("Cluster " + (i + 1) + " center : " + cCenter);
			int clusterSize = cPoints.size();
			System.out.println("Cluster " + (i + 1) + " size : " + clusterSize);
			clusterListOfKDependentAlgos.add(cluster);
		}
		return clusterListOfKDependentAlgos;
	}

	/**
	 * 
	 * @param clusters
	 *            a input
	 * @return List of clusters
	 */
	public List<Cluster<DataPoint>> displayingAndConvetinForDBSCAN(List<Cluster<DataPoint>> clusters) {
		int i = 0;
		while (clusters.isEmpty() == false) {
			Cluster<DataPoint> cluster = clusters.get(i);
			List<DataPoint> cPoints = cluster.getPoints();
			System.out.println("Cluster " + (i + 1) + " data : " + cPoints);
			int clusterSize = cPoints.size();
			System.out.println("Cluster " + (i + 1) + " size : " + clusterSize);
			clusterListOfDBSCANDependentAlgo.add(cluster);
		}
		return clusterListOfDBSCANDependentAlgo;
	}
}