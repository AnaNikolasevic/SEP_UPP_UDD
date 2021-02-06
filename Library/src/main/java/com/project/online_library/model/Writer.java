package com.project.online_library.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Writer extends Users{

    @Column
    private Boolean publishEnabled;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<BookPrototype> bookPrototypeList;
    
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MandatoryBook> mandatoryBookList;
    
    @ManyToMany
	@JoinTable(
	  name = "writer_genre", 
	  joinColumns = @JoinColumn(name = "users_id_seq"), 
	  inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private List<Genre> genreList;
    
    public Writer() {
    }

    public Writer(String firstName, String lastName, String email, String password, String username, String city, String country, boolean enabled, List<Genre> genreList, Boolean publishEnabled) {
        super(firstName, lastName, email, password, username, city, country, enabled);
        this.genreList = genreList;
        this.publishEnabled = publishEnabled;
    }

    public List<BookPrototype> getBookPrototypeList() {
        return bookPrototypeList;
    }

    public void setBookPrototypeList(List<BookPrototype> bookPrototypeList) {
        this.bookPrototypeList = bookPrototypeList;
    }

	public List<MandatoryBook> getMandatoryBookList() {
		return mandatoryBookList;
	}

	public void setMandatoryBookList(List<MandatoryBook> mandatoryBookList) {
		this.mandatoryBookList = mandatoryBookList;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

    public Boolean getPublishEnabled() {
        return publishEnabled;
    }

    public void setPublishEnabled(Boolean publishEnabled) {
        this.publishEnabled = publishEnabled;
    }
}
