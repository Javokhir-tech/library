package com.java.library.service.impl;

import com.java.library.dto.GenreDTO;
import com.java.library.dto.mapper.GenreMapper;
import com.java.library.repository.GenreRepository;
import com.java.library.service.GenreService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreDTO addGenre(GenreDTO genreDTO) {
        return genreMapper.toDto(genreRepository.save(genreMapper.toEntity(genreDTO)));
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreMapper.toDTOList(genreRepository.findAll());
    }
}
