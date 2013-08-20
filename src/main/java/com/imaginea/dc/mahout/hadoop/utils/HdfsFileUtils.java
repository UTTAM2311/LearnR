package com.imaginea.dc.mahout.hadoop.utils;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsFileUtils {
	
	private String HDFS_URI = "hdfs://localhost:54310/";
	private String HDFS_NAME = "fs.default.name";

	private static FileSystem fileSystem;
	
	public HdfsFileUtils() {
		Configuration conf = new Configuration();
		conf.set(HDFS_NAME,HDFS_URI);
		try {
			fileSystem = FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HdfsFileUtils(String hdfsName, String hdfsUri) {
		Configuration conf = new Configuration();
		conf.set(hdfsName,hdfsUri);
		try {
			fileSystem = FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void copyFromLocal(String localSource, String hdfsDestination) throws IOException {
		Path src = new Path(localSource);
		Path dst = new Path(hdfsDestination);
		fileSystem.copyFromLocalFile(src, dst);
	}
	
	public void copyToLocal(String hdfsSource, String localDestination) throws IOException {
		Path src = new Path(hdfsSource);
		Path dst = new Path(localDestination);
		fileSystem.copyToLocalFile(src, dst);
	}
	
	public Configuration getConfiguration() {
		return fileSystem.getConf();
	}
	
	public FileSystem getFileSystem() {
		return fileSystem;
	}

}
