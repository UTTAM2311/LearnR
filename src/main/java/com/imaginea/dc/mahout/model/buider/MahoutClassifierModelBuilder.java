package com.imaginea.dc.mahout.model.buider;

public interface MahoutClassifierModelBuilder {
	public void generateSequenceFile() throws ModelBuilderException;
	public void generateSequenceToSparseFile() throws ModelBuilderException; 
	public void genarateModelFile() throws ModelBuilderException; 
	public void buildModel() throws ModelBuilderException; 
}
