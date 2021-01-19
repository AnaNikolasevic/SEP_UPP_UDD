package com.project.online_library.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class BetaReader extends Reader{

	@ManyToMany
	@JoinTable(
	  name = "reader_genre_beta", 
	  joinColumns = @JoinColumn(name = "users_id_seq"), 
	  inverseJoinColumns = @JoinColumn(name = "genre_beta_id"))
	private List<Genre> genreBetaList;

	@OneToMany(mappedBy = "betaReader", fetch = FetchType.LAZY)
	private List<BookPrototype> bookPrototypeList;

	@OneToMany(mappedBy = "betaReader", fetch = FetchType.LAZY)
	private List<Comment> comments;

	@Column
	private int penaltyPoints;
	
    public BetaReader() {
    }

	public BetaReader(String firstName, String lastName, String email, String password, String username, String city,
			String country, boolean enabled, List<Genre> genreList, List<Genre> genreBetaList) {
		super(firstName, lastName, email, password, username, city, country, enabled, genreList);
		this.genreBetaList = genreBetaList;
	}

	public List<Genre> getGenreBetaList() {
		return genreBetaList;
	}

	public void setGenreBetaList(List<Genre> genreBetaList) {
		this.genreBetaList = genreBetaList;
	}

	public List<BookPrototype> getBookPrototypeList() {
		return bookPrototypeList;
	}

	public void setBookPrototypeList(List<BookPrototype> bookPrototypeList) {
		this.bookPrototypeList = bookPrototypeList;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getPenaltyPoints() {
		return penaltyPoints;
	}

	public void setPenaltyPoints(int penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}
}
