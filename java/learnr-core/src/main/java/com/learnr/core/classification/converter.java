package com.learnr.core.classification;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.classifier.sgd.CsvRecordFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;

public class converter {
	public static void csvtovector() throws IOException {
		String filename="donut.csv";
		Splitter onComma = Splitter.on(",");
		Map<String, String> map = new HashMap<String, String>();
		List<Integer> order = Lists.newArrayList();
		List<String> raw = Resources.readLines(Resources.getResource(filename), Charsets.UTF_8);
		for (String line : raw.subList(0, raw.size())) {
			order.add(order.size());
		CsvRecordFactory con=new CsvRecordFactory(line, null);
	}
	}
}
