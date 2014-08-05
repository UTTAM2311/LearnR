package com.learnr.core.classification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.mahout.classifier.sgd.AbstractOnlineLogisticRegression;
import org.apache.mahout.classifier.sgd.CrossFoldLearner;
import org.apache.mahout.classifier.sgd.L2;
import org.apache.mahout.classifier.sgd.OnlineLogisticRegression;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.vectorizer.encoders.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.learnr.core.plot.PlotTest;

public class logistic {

	public static int noOfFeatures = 13;
	public static int noOfCatogeries = 2;
	private static final Logger logger = LoggerFactory.getLogger(PlotTest.class);

	// public static String filename = "donut.csv";

	public static void log(String inputDataFilename, int noOfFeatures, int noOfCategories) throws IOException {

		String filename = inputDataFilename;
		Splitter onComma = Splitter.on(",");

		List<String> raw = Resources.readLines(Resources.getResource(filename), Charsets.UTF_8);
		List<Vector> data = Lists.newArrayList();
		List<Integer> target = Lists.newArrayList();
		Dictionary dict = new Dictionary();
		List<Integer> order = new ArrayList<Integer>();
		System.out.println(" size of the input data" + raw.size());
		for (String l : raw.subList(1, raw.size())) {
			System.out.println(l);
			order.add(order.size());
			System.out.println(order + "order");
			Vector v = new DenseVector(noOfFeatures + 1);
			v.set(0, 1);
			int i = 1;
			Iterable<String> values = onComma.split(l);
			for (String value : Iterables.limit(values, noOfFeatures)) {
				v.set(i++, Double.parseDouble(value));
				System.out.println(value);
			}
			System.out.println("vector" + v);
			data.add(v);
			System.out.println("input data" + data);
			target.add(dict.intern(Iterables.get(values, noOfFeatures - 1)));
			System.out.println(target + "target value");
			System.out.println(Iterables.get(values, noOfFeatures - 1) + "get value");

		}
		Random random = new Random();
		Collections.shuffle(order, random);
		System.out.println(order);
		List<Integer> trainData = order.subList(0, raw.size() - 1);
		List<Integer> test = order.subList(20, 30);
		logger.warn("Training set = {}", trainData);
		logger.warn("Test set = {}", test);
		int[] correct = new int[test.size() + 1];
		for (int run = 0; run < 1; run++) {
			@SuppressWarnings("resource")
			AbstractOnlineLogisticRegression lr = new OnlineLogisticRegression(noOfCatogeries, noOfFeatures + 1,
					new L2(1));
			// @SuppressWarnings("resource")
			// CrossFoldLearner cfl = new CrossFoldLearner();
			// cfl.addModel(lr);
			for (int pass = 0; pass < 1; pass++) {
				Collections.shuffle(trainData, random);
				for (int k : trainData) {
					// System.out.println(target.size()+"   "+data.size());
					// System.out.println(k);
					// System.out.println(target.get(k));
					// System.out.println(data.get(k));
					lr.train(target.get(k), data.get(k));
					// System.out.println(lr.link(data.get(k)));

				}

				System.out.println("Break" + trainData.size());
			}
			int x = 0;
			int[] count = new int[noOfCatogeries];
			for (Integer k : test) {
				int r = lr.classifyFull(data.get(k)).maxValueIndex();
				// System.out.println(cfl.logLikelihood(target.get(k),
				// data.get(k)));
				// System.out.println(lr.classifyFull(data.get(k)));
				// System.out.println(r);
				count[r]++;
				x += r == target.get(k) ? 1 : 0;
			}
			// System.out.println("Break" + trainData.size());
			// System.out.println(count[0] + "  " + count[1] + "  " + count[2]);
			correct[x]++;

			// System.out.println(x+"  "+ correct[x]);
		}
	}
}
