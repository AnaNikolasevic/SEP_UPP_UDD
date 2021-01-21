package com.project.online_library.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.online_library.dto.MandatoryBookDTO;
import com.project.online_library.dto.WriterDTO;
import com.project.online_library.model.BoardMember;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.model.MandatoryBook;
import com.project.online_library.repository.BoardMemberRepository;
import com.project.online_library.repository.MandatoryBookRepository;
import com.project.online_library.service.WriterService;

@CrossOrigin
@RestController
@RequestMapping("/writer")
public class MandatoryBookController {

	@Autowired
	WriterService writerService;

	@GetMapping(path = "", produces = "application/json")
    public ResponseEntity<?> getAll() {
		ArrayList<WriterDTO> writers = writerService.getAll();
        return new ResponseEntity<>(writers, HttpStatus.OK);
    }
	

}
