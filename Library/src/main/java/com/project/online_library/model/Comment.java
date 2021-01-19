package com.project.online_library.model;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String commentText;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private BookPrototype bookPrototype;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private BetaReader betaReader;

    public Comment (){}


    public BookPrototype getBookPrototype() {
        return bookPrototype;
    }

    public void setBookPrototype(BookPrototype book) {
        this.bookPrototype = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }



    public BetaReader getBetaReader() {
        return betaReader;
    }

    public void setBetaReader(BetaReader betaReader) {
        this.betaReader = betaReader;
    }
}
