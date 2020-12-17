package com.project.online_library.dto;

import java.io.Serializable;

public class FormSubmissionDto implements Serializable{
	
	String id;
	String fieldValue;
	
	
	public FormSubmissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormSubmissionDto(String fieldId, String fieldValue) {
		super();
		this.id = fieldId;
		this.fieldValue = fieldValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String fieldId) {
		this.id = fieldId;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}


	
	
}
