package com.project.online_library.controller;

import com.project.online_library.dto.BookPrototypeDTO;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.service.BookPrototypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bookPrototype")
public class BookPreviewController {

    @Autowired
    private BookPrototypeService bookPrototypeService;

    @GetMapping("/editor/{username}")
    public ResponseEntity<List<BookPrototypeDTO>> getAllEditorsBookPrototypes(@PathVariable String username) {
        return new ResponseEntity<List<BookPrototypeDTO>>(bookPrototypeService.getAllEditorsBookPrototypes(username), HttpStatus.OK);
    }
}
