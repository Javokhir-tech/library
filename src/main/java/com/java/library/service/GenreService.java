package com.java.library.service;

import com.java.library.domain.Genre;
import com.java.library.dto.BookDTO;
import com.java.library.dto.GenreDTO;

import java.util.List;
import java.util.Set;

public interface GenreService {

    GenreDTO addGenre(GenreDTO genreDTO);

    List<GenreDTO> getAllGenres();

    void deleteGenre(Long id);

    Set<Genre> addGenresToBook(BookDTO bookDTO);
}
