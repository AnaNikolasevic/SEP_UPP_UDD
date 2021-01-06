package com.project.online_library.repository;

import com.project.online_library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenreRepository extends JpaRepository<Genre,Long>{
	Genre findByName(String name);
}
