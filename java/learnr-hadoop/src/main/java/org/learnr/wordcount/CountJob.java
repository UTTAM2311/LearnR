package org.learnr.wordcount;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.learnr.core.text.Lemmatizer;

public class CountJob extends Configured implements Tool {

	public static Path path = new Path("/home/cloudera/ap-sample.txt");
	public static String output = "/home/cloudera/out/";

	public static class MapClass extends MapReduceBase implements
			Mapper<LongWritable, Text, Text, LongWritable> {

		@Override
		public void map(LongWritable key, Text value,
				OutputCollector<Text, LongWritable> output, Reporter reporter)
				throws IOException {

			String line = value.toString();
			
			List<String> lemmList = Lemmatizer.lemmatize(line);
			for (String lemma : lemmList) {
				output.collect(new Text(lemma.toLowerCase()), new LongWritable(1L));
			}
		}

	}

	public static class ReduceClass extends MapReduceBase implements
			Reducer<Text, LongWritable, Text, LongWritable> {

		@Override
		public void reduce(Text key, Iterator<LongWritable> values,
				OutputCollector<Text, LongWritable> output, Reporter reporter)
				throws IOException {
			// TODO Auto-generated method stub
			int sum = 0;
			while (values.hasNext()) {
				sum = (int) (sum + values.next().get());
			}
			output.collect(key, new LongWritable(sum));
		}
	}

	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		JobConf job = new JobConf(conf, CountJob.class);
		job.setJobName("WordCount");

		String op = output + System.currentTimeMillis();
		FileInputFormat.setInputPaths(job, path);
		FileOutputFormat.setOutputPath(job, new Path(op));

		job.setMapperClass(MapClass.class);
		job.setReducerClass(ReduceClass.class);
		job.setInputFormat(TextInputFormat.class);
		job.setOutputFormat(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		JobClient.runJob(job);

		return 0;
	}

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new CountJob(), args);
		System.exit(res);
	}
}
