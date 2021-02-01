package com.project.online_library.dto;

import java.io.Serializable;

public class FormSubmissionDto implements Serializable{
	
	String id;
	Object fieldValue;
	
	
	public FormSubmissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormSubmissionDto(String fieldId, Object fieldValue) {
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

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}


	
	
}
