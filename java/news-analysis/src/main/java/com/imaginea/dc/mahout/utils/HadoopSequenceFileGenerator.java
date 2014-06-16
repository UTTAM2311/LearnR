package com.imaginea.dc.mahout.utils;

import java.io.IOException;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;

public class HadoopSequenceFileGenerator {
	private Writer writer;

	private Text key;
	private Text value;

	public HadoopSequenceFileGenerator(String sequenceFileDirectory) throws IOException {
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		writer = new SequenceFile.Writer(fs, configuration, new Path(sequenceFileDirectory), Text.class, Text.class);
		key = new Text();
		value = new Text();
	}

	public void addData(String label, String data) throws IOException {
		key.set("/" + label + "/" + UUID.randomUUID());
		value.set(data);
		writer.append(key, value);
	}

	public void finalise() throws IOException {
		writer.close();
	}

}
