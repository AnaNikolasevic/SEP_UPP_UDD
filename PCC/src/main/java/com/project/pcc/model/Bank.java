package com.project.pcc.model;


import javax.persistence.*;

@Entity
public class Bank {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column
    private String name;

    @Column
    private String panID;

    @Column
    private String url;

    public Bank() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPanID() {
        return panID;
    }

    public void setPanID(String panID) {
        this.panID = panID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
