package com.java.library.service;

import com.java.library.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    GenreDTO addGenre(GenreDTO genreDTO);

    List<GenreDTO> getAllGenres();

    void deleteGenre(Long id);
}
