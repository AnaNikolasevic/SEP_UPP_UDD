package com.project.online_library.dto;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.project.online_library.model.MandatoryBook;
import com.project.online_library.model.Writer;

public class MandatoryBookDTO {

    private Long id;

    private String title;

    private String path;
    
    private Long writer_id;

	public MandatoryBookDTO(MandatoryBook mandatoryBook) {
		super();
		this.id = mandatoryBook.getId();
		this.title = mandatoryBook.getTitle();
		this.path = mandatoryBook.getPath();
		this.writer_id = mandatoryBook.getWriter().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(Long writer_id) {
		this.writer_id = writer_id;
	}

	public MandatoryBookDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


    
    
}
