package com.project.online_library.dto;

import com.project.online_library.model.BookPrototype;

public class BookPrototypeDTO {

    private Long id;
    private String title;
    private String synopsis;
    private String genre;

    public BookPrototypeDTO(){
        super();
    }

    public BookPrototypeDTO(BookPrototype bookPrototype){
        super();
        this.id = bookPrototype.getId();
        this.title = bookPrototype.getTitle();
        this.synopsis = bookPrototype.getSynopsis();
        this.genre = bookPrototype.getGenre().getName();
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
