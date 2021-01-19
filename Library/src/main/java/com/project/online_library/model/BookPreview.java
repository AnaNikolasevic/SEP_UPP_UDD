package com.project.online_library.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class BookPreview {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String path;

    @OneToMany(mappedBy ="boardMember", fetch = FetchType.LAZY)
    private List<BoardMember> boardMembers;
}
