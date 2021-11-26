package com.java.library.controller;

import com.java.library.dto.GenreDTO;
import com.java.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    // admen
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    // admen
    @PostMapping
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genreDTO) {
        return ResponseEntity.ok(genreService.addGenre(genreDTO));
    }

    // admen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("id") Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.ok().build();
    }
}
