package com.project.coingate.model;
import javax.persistence.*;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String coingateToken;

    @Column
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCoingateToken() {
		return coingateToken;
	}

	public void setCoingateToken(String coingateToken) {
		this.coingateToken = coingateToken;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
