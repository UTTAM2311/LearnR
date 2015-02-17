package com.learnr.core.classification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.classifier.evaluation.Auc;
import org.apache.mahout.classifier.sgd.AbstractOnlineLogisticRegression;
import org.apache.mahout.classifier.sgd.L2;
import org.apache.mahout.classifier.sgd.OnlineLogisticRegression;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.learnr.core.plot.SimplePlotTest;

public class LogReg {
	private static final Logger logger = LoggerFactory.getLogger(LogReg.class);
	public static Map<Vector, Integer> trainingData;
	public static int noOfFeatures;
	public static int noOfCategories;

	/**
	 * 
	 * @param trainingData
	 *            --- A mapping between input features and target values
	 * @param noOfFeatures
	 *            --- number of input features
	 * @param noOfCategories
	 *            ---number of categories the data can be classified
	 * @return returns a training model
	 */
	public static AbstractOnlineLogisticRegression trainModel(Map<Vector, Integer> trainingData, int noOfFeatures,
			int noOfCategories) {
		LogReg.trainingData = trainingData;
		LogReg.noOfFeatures = noOfFeatures;
		LogReg.noOfCategories = noOfCategories;
		Auc x = new Auc(0.75);

		List<Vector> data = new ArrayList<Vector>();
		data.addAll(trainingData.keySet());

		List<Integer> target = new ArrayList<Integer>();
		target.addAll(trainingData.values());

		logger.info("data size:" + data.size());

		AbstractOnlineLogisticRegression model = new OnlineLogisticRegression(noOfCategories, noOfFeatures + 1, new L2(
				1));

		for (int k = 0; k < data.size(); k++) {
			logger.info("vector " + data.get(k) + "\t target value " + target.get(k));
			model.train(target.get(k), data.get(k));

		}
		for (int k = 0; k < data.size(); k++) {
			x.add(target.get(k), model.classifyFull(data.get(k)).maxValueIndex());
		}
		logger.info("auc value:" + x.auc());
		logger.info("confusion matrix:" + x.confusion());
		return model;
	}

	/**
	 * Converts the testfile into a vector list
	 * 
	 * @param Testfile
	 *            ---training data file
	 * @return returns the training data file into a input vectors for running
	 *         in the test model
	 * @throws IOException
	 */
	public static List<Vector> convertTestfile(String Testfile) throws IOException {
		Splitter onComma = Splitter.on(",");

		List<String> raw = Resources.readLines(Resources.getResource(Testfile), Charsets.UTF_8);
		List<Vector> data = Lists.newArrayList();
		List<Integer> order = Lists.newArrayList();
		for (String line : raw.subList(0, raw.size())) {
			order.add(order.size());
			Vector v = new DenseVector(noOfFeatures + 1);
			v.set(0, 1);
			int i = 1;
			Iterable<String> values = onComma.split(line);
			for (String value : Iterables.limit(values, noOfFeatures)) {
				v.set(i++, Float.parseFloat(value));
			}
			System.out.println("input vectors from test-data\t" + v);
			data.add(v);
		}
		return data;
	}

	/**
	 * 
	 * @param data
	 *            ---dataset of the input
	 * 
	 */
	public static void test(List<Vector> data) {
		int x = 0;
		AbstractOnlineLogisticRegression model = LogReg.trainModel(trainingData, noOfFeatures, noOfCategories);
		List<Integer> target = Lists.newArrayList();
		Map<Vector, Integer> map = new HashMap<Vector, Integer>();
		// List<Integer> test = order.subList(0, order.size());
		int r;
		int[] count = new int[noOfCategories];
		for (int k = 0; k < data.size(); k++) {
			r = model.classifyFull(data.get(k)).maxValueIndex();
			target.add(k, r);
			count[r]++;
			x += r == target.get(k) ? 1 : 0;
			map.put(data.get(k), target.get(k));
			System.out.println("vector from testdata" + data.get(k));
			System.out.println("\t target value for the given test vector" + target.get(k));
		}

	}
}
