package com.imaginea.dc.mahout.model.buider;


public abstract class AbstactMahoutClassifierModelBuilder implements MahoutClassifierModelBuilder{

	public abstract void generateSequenceFile() throws ModelBuilderException ;

	public abstract void generateSequenceToSparseFile() throws ModelBuilderException ;

	public abstract void genarateModelFile() throws ModelBuilderException;

	public void buildModel() throws ModelBuilderException {
		generateSequenceFile();
		generateSequenceToSparseFile();
		genarateModelFile();
	}

}
