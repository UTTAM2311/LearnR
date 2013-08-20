package com.imaginea.dc.mahout.model.buider;

public class ModelBuilderException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ModelBuilderException() {
		super();
	}
	public ModelBuilderException(String message) {
		super(message);
	}
	public ModelBuilderException(String message, Exception cause) {
		super(message, cause);
	}

}
