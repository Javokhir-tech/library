package com.java.library.controller;

import com.java.library.domain.Book;
import com.java.library.dto.BookDTO;
import com.java.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getOneBook(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(bookService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok().body(bookService.createBook(bookDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
}
