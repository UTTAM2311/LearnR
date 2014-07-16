package com.learnr.clustering;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.junit.Assert;
import org.junit.Test;

import com.learnr.util.TextFileUtility;

public class ClusterTest {
	private static final String CLUSTER_DATASET_2D_1 = "C:\\Users\\manojm\\Desktop\\s1.txt";
	private static final String CLUSTER_DATASET_2D_3 = "C:\\Users\\manojm\\Desktop\\s2.txt";
	private RealMatrix s1data, s2data;
	private int noOfClusters = 4;
	double fuzzinessFactor = 1.5;
	List<CentroidCluster<DataPoint>> ListSpiralKmeansPlusPLusClustersSpialData = new ArrayList<CentroidCluster<DataPoint>>();
	List<CentroidCluster<DataPoint>> ListSpiralMultiKmeansPlusPLusClustersSpiralData = new ArrayList<CentroidCluster<DataPoint>>();
	List<CentroidCluster<DataPoint>> ListSpiralFuzzyKmeansClustersSpiralData = new ArrayList<CentroidCluster<DataPoint>>();
	List<CentroidCluster<DataPoint>> ListSpiralKmeansPlusPLusClustersS2Data = new ArrayList<CentroidCluster<DataPoint>>();
	List<CentroidCluster<DataPoint>> ListSpiralMultiKmeansPlusPLusClustersS2Data = new ArrayList<CentroidCluster<DataPoint>>();
	List<CentroidCluster<DataPoint>> ListSpiralFuzzyKmeansClustersS2Data = new ArrayList<CentroidCluster<DataPoint>>();

	@Test
	public void testingData() {
		s1data = TextFileUtility.readMatrixFromTextFile(CLUSTER_DATASET_2D_1);
		s2data = TextFileUtility.readMatrixFromTextFile(CLUSTER_DATASET_2D_3);
		ClustersGenerator cgspiral = new ClustersGenerator(s1data);
		ListSpiralKmeansPlusPLusClustersSpialData = cgspiral.generaterKMeansPlusPlusClusters(noOfClusters);
		ListSpiralMultiKmeansPlusPLusClustersSpiralData = cgspiral
				.generaterMultipleKMeansPlusPlusClusters(noOfClusters);
		ListSpiralFuzzyKmeansClustersSpiralData = cgspiral.generateFuzzyKMeansClusters(noOfClusters, fuzzinessFactor);
		Assert.assertEquals(4, ListSpiralKmeansPlusPLusClustersSpialData.size());
		Assert.assertEquals(4, ListSpiralMultiKmeansPlusPLusClustersSpiralData.size());
		Assert.assertEquals(4, ListSpiralFuzzyKmeansClustersSpiralData.size());
		ClustersGenerator cgs2 = new ClustersGenerator(s2data);
		ListSpiralKmeansPlusPLusClustersS2Data = cgs2.generaterKMeansPlusPlusClusters(noOfClusters);
		ListSpiralMultiKmeansPlusPLusClustersS2Data = cgs2.generaterMultipleKMeansPlusPlusClusters(noOfClusters);
		ListSpiralFuzzyKmeansClustersS2Data = cgs2.generateFuzzyKMeansClusters(noOfClusters, fuzzinessFactor);
		Assert.assertEquals(4, ListSpiralKmeansPlusPLusClustersS2Data.size());
		Assert.assertEquals(4, ListSpiralMultiKmeansPlusPLusClustersS2Data.size());
		Assert.assertEquals(4, ListSpiralFuzzyKmeansClustersS2Data.size());

	}
}
