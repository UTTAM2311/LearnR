package org.learnr.spark;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

		// Initialize spark context
		SparkConf conf = new SparkConf().setAppName("test app").setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(conf);

		List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
		JavaRDD<Integer> distData = sc.parallelize(data);

		JavaRDD<String> distFile = sc.textFile("/home/milli/Downloads/data.txt");


		JavaRDD<String> lines = sc.textFile("/home/milli/Downloads/data.txt");
		JavaRDD<Integer> lineLengths = lines.map(new Function<String, Integer>() {
			public Integer call(String s) {
				return s.length();
			}
		});
		
		int totalLength = lineLengths.reduce(new Function2<Integer, Integer, Integer>() {
			public Integer call(Integer a, Integer b) {
				return a + b;
			}
		});
		
		System.out.println(totalLength);

	}

}
