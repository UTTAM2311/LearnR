package com.imaginea.dc.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "svm_model")
@NamedQueries({
	@NamedQuery(name = "svm_model.fetch_latest_model", 
			query = "SELECT instance FROM SVMModel instance WHERE instance.modelVersion = (SELECT max(modelVersion) from SVMModel)")
	
})
public class SVMModel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int modelVersion;
	private byte[] model;
	
	public int getModelVersion() {
		return modelVersion;
	}
	public void setModelVersion(int modelVersion) {
		this.modelVersion = modelVersion;
	}
	
	@Lob
	public byte[] getModel() {
		return model;
	}
	public void setModel(byte[] model) {
		this.model = model;
	}
	
	
}
