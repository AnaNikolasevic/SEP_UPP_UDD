package com.project.online_library.repository;

import com.project.online_library.model.Editor;
import com.project.online_library.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorRepository extends JpaRepository<Editor, Long> {

    Editor findByUsername(String username);
}
