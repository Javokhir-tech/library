package com.java.library.controller;

import com.java.library.dto.AuthorBooksDTO;
import com.java.library.dto.AuthorDTO;
import com.java.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorBooksDTO> getAuthorsAllBooks(@PathVariable("id") long id) {
        return ResponseEntity.ok(authorService.getAuthorsAllBooks(id));
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.addAuthor(authorDTO));
    }
}
