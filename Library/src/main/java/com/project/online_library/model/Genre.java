package com.project.online_library.model;
import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="genre_id")
    private Long id;

    @Column
    private String name;

	@OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
	private List<BookPrototype> bookPrototypeList;

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

	public List<BookPrototype> getBookPrototypeList() {
		return bookPrototypeList;
	}

	public void setBookPrototypeList(List<BookPrototype> bookPrototypeList) {
		this.bookPrototypeList = bookPrototypeList;
	}
}
