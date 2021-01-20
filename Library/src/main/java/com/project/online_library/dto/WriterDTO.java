package com.project.online_library.dto;

import java.util.List;


import com.project.online_library.model.BookPrototype;
import com.project.online_library.model.MandatoryBook;

public class WriterDTO {
	private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String username;
    
    
    private List<MandatoryBookDTO> mandatoryBookList;

	public WriterDTO(Long id, String firstName, String lastName, String email, String password, String username,
			List<MandatoryBookDTO> mandatoryBookList) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.username = username;
		this.mandatoryBookList = mandatoryBookList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<MandatoryBookDTO> getMandatoryBookList() {
		return mandatoryBookList;
	}

	public void setMandatoryBookList(List<MandatoryBookDTO> mandatoryBookList) {
		this.mandatoryBookList = mandatoryBookList;
	}

	public WriterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
