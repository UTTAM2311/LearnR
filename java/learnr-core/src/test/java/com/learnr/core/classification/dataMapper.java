package com.learnr.core.classification;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

/**
 * CONVERTS THE INPUT STRING FILE INTO A MAPPING BETWEEN VECTORS AND INTEGER
 * VALUES
 * 
 * @author
 *
 */
public class dataMapper {
	private static final Logger logger = LoggerFactory.getLogger(PlotTest.class);


	/**
	 * convert a datafile into mapping between features and target value
	 * 
	 * @param filename
	 *            --- Input data file (note that the last term should be a
	 *            target variable)
	 * @param noOfFeatures
	 *            --number of input features
	 * @return returns a map containing input features as a vector and target
	 *         variable as Integer
	 * @throws IOException
	 */
	public Map<Vector, Integer> getVectorsAndTargetValues(String filename, int noOfFeatures) throws IOException {
		Map<Vector, Integer> map = new HashMap<Vector, Integer>();
		Splitter onComma = Splitter.on(",");
		List<String> raw = Resources.readLines(Resources.getResource(filename), Charsets.UTF_8);
		List<Vector> data = Lists.newArrayList();
		List<Integer> target = Lists.newArrayList();
		Dictionary dict = new Dictionary();
		List<Integer> order = Lists.newArrayList();
		int count = 1;
		for (String line : raw.subList(0, raw.size())) {
			order.add(order.size());
			Vector v = new DenseVector(noOfFeatures + 1);
			v.set(0, 1);
			int i = 1;
			Iterable<String> values = onComma.split(line);
			for (String value : Iterables.limit(values, noOfFeatures)) {
				v.set(i++, Float.parseFloat(value));
			}
			System.out.println(v);
			data.add(v);

			target.add(dict.intern(Iterables.get(values, noOfFeatures)));
			System.out.println("input vectors" + count + " " + v);
			count++;
			for (int j = 0; j < data.size(); j++) {
				map.put(data.get(j), target.get(j));
			}
		}
		return map;
	}
}
