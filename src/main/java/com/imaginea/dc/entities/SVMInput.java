package com.imaginea.dc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "svm_input")
@NamedQueries({
	@NamedQuery(name = "svm_input.deleteall", 
			query = "delete from SVMInput")
})

public class SVMInput extends BaseEntity {

	private String inputLine;

	@Lob
	public String getInputLine() {
		return inputLine;
	}

	public void setInputLine(String inputLine) {
		this.inputLine = inputLine;
	}

}
