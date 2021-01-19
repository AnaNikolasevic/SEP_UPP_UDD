package com.project.online_library.repository;

import com.project.online_library.model.BoardMember;
import com.project.online_library.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardMemberRepository extends JpaRepository<BoardMember, Long> {

    BoardMember findByUsername(String username);

}
