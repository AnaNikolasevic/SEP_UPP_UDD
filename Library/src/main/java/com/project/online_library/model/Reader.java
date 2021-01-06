package com.project.online_library.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Reader extends Users{

	@ManyToMany
	@JoinTable(
	  name = "reader_genre", 
	  joinColumns = @JoinColumn(name = "users_id_seq"), 
	  inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private List<Genre> genreList;
	
    public Reader() {
        super();
    }

	public Reader(String firstName, String lastName, String email, String password, String username, String city,
			String country, boolean enabled, List<Genre> genreList) {
		super(firstName, lastName, email, password, username, city, country, enabled);
		this.genreList = genreList;
	}


}
