package com.project.online_library.model;

public class BoardMember extends Users{


    public BoardMember(String firstName, String lastName, String email, String password, String username, String city, String country, boolean enabled) {
        super(firstName, lastName, email, password, username, city, country, enabled);
    }
}
