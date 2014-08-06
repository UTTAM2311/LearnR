package com.learnr.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExcelColumn {

	/**
	 * Name of the column to map the annotated property with.
	 * 
	 * @return A column name in the Excel sheet
	 */
	public String name();
	
	// TODO extend this to use ElementType.Method also
	
}
