package com.learnr.core.clustering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.core.text.TermFreqVector;

/**
 * Cluster Utilities
 */
public class ClusterUtil {

	private static final Logger logger = LoggerFactory.getLogger(ClusterUtil.class);

	/**
	 * 
	 * @param cluster
	 * @return returns array of distances from center to points
	 */
	public static Double[] forCluster(CentroidCluster<Clusterable> cluster) {
		Double[] distances = new Double[cluster.getPoints().size()];
		if (cluster != null || cluster.getPoints() != null) {
			Clusterable clusterCenter = cluster.getCenter();
			for (int i = 0; i < cluster.getPoints().size(); i++) {
				System.out.println("no of points: " + cluster.getPoints().size());
				Clusterable point = cluster.getPoints().get(i);
				Double distance = getDistance(clusterCenter, point);
				distances[i] = distance;
				logger.debug("distances : " + distances[i]);
			}
		}
		return distances;
	}

	/**
	 * 
	 * @param clusters
	 * @return returns a list containing distances of each clustercenter to its corresponding cluster point
	 */
	public static List<Double[]> forAllClusters(List<CentroidCluster<Clusterable>> clusters) {
		List<Double[]> arrayOfDistances = new ArrayList<Double[]>();
		for (int index = 0; index < clusters.size(); index++) {
			System.out.println("belongs to cluster " + (index + 1));
			CentroidCluster<Clusterable> cluster = clusters.get(index);
			arrayOfDistances.add(ClusterUtil.forCluster(cluster));
		}
		return arrayOfDistances;
	}

	/**
	 * 
	 * @param clusterCenter
	 * @param point
	 * @return returns Euclideandistance between clustercenter and clusterpoint
	 */
	private static double getDistance(Clusterable clusterCenter, Clusterable point) {
		EuclideanDistance calc = new EuclideanDistance();
		double distance = calc.compute(clusterCenter.getPoint(), point.getPoint());
		return distance;
	}

	/**
	 * 
	 * @param clusters
	 * @return
	 */

	public static List<List<Clusterable>> getvectors(List<CentroidCluster<Clusterable>> clusters) {
		List<List<Clusterable>> allPoints = new ArrayList<List<Clusterable>>();
		for (int i = 0; i < clusters.size(); i++) {
			allPoints.add(clusters.get(i).getPoints());
		}
		return allPoints;

	}

	/**
	 * 
	 * @param vectors
	 * @return
	 */

	public List<String> getIDs(List<TermFreqVector<String>> vectors) {
		List<String> IDs = new ArrayList<String>();
		for (TermFreqVector<String> vector : vectors) {
			IDs.add(vector.getId());
		}
		return IDs;

	}

	/**
	 * 
	 * @param vectors
	 * @return
	 */
	public Map<String, double[]> getPoints(List<TermFreqVector<String>> vectors) {
		Map<String, double[]> allPoints = new HashMap<String, double[]>();
		for (int i = 0; i < vectors.size(); i++) {
			allPoints.put(vectors.get(i).getId(), vectors.get(i).getPoint());
		}
		return allPoints;
	}

	/**
	 * 
	 * @param allPoints
	 * @return
	 */
	public Map<Double, String> getDistance(Map<String, double[]> allPoints, List<String> IDs) {
		Map<Double, String> map = new TreeMap<Double, String>();
		EuclideanDistance calc = new EuclideanDistance();
		double[] distances = new double[allPoints.size()];
		for (int i = 0; i < allPoints.size(); i++) {
			distances[i] = calc.compute(allPoints.get(IDs.get(15)), allPoints.get(IDs.get(i)));
			map.put(distances[i], IDs.get(i));
		}
		Arrays.sort(distances);
		return map;
	}

}
