package com.imaginea.dc.mahout.classifier;

import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public interface MahoutClassifier {
	public Map<String, Integer> readDictionnary(Configuration conf, Path dictionnaryPath) ;
	public Map<Integer, Long> readDocumentFrequency(Configuration conf, Path documentFrequencyPath) ;
}
