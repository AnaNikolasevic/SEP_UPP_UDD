package com.project.online_library.repository;

import com.project.online_library.model.Editor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorRepository extends JpaRepository<Editor, Long> {
}
