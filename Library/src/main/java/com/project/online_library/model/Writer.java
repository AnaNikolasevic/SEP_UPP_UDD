package com.project.online_library.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Writer extends Users{

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<BookPrototype> bookPrototypeList;

    public Writer() {
    }

    public Writer(String firstName, String lastName, String email, String password, String username, String city, String country, boolean enabled) {
        super(firstName, lastName, email, password, username, city, country, enabled);
    }

    public List<BookPrototype> getBookPrototypeList() {
        return bookPrototypeList;
    }

    public void setBookPrototypeList(List<BookPrototype> bookPrototypeList) {
        this.bookPrototypeList = bookPrototypeList;
    }
}
