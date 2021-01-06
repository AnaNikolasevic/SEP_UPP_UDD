package com.project.online_library.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class BetaReader extends Reader{

	@ManyToMany
	@JoinTable(
	  name = "reader_genre_beta", 
	  joinColumns = @JoinColumn(name = "users_id_seq"), 
	  inverseJoinColumns = @JoinColumn(name = "genre_beta_id"))
	private List<Genre> genreBetaList;
	
    public BetaReader() {
    }

	public BetaReader(String firstName, String lastName, String email, String password, String username, String city,
			String country, boolean enabled, List<Genre> genreList, List<Genre> genreBetaList) {
		super(firstName, lastName, email, password, username, city, country, enabled, genreList);
		this.genreBetaList = genreBetaList;
	}

    

    
}
