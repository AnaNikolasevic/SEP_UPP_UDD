package com.project.online_library.model;

import javax.persistence.Entity;

@Entity
public class BetaReader extends Reader{

    public BetaReader() {
    }

    public BetaReader(String firstName, String lastName, String email, String password, String username, String city, String country, boolean enabled) {
        super(firstName, lastName, email, password, username, city, country, enabled);
    }
}
