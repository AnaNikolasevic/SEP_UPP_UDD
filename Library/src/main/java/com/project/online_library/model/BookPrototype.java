package com.project.online_library.model;

import com.project.online_library.enums.BookStatus;

import javax.persistence.*;
import java.util.List;

@Entity
public class BookPrototype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String synopsis;

    @Column
    private BookStatus bookStatus;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    public Genre genre;

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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}