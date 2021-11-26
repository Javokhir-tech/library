package com.java.library.service.impl;

import com.java.library.domain.Genre;
import com.java.library.dto.BookDTO;
import com.java.library.dto.GenreDTO;
import com.java.library.dto.mapper.GenreMapper;
import com.java.library.repository.GenreRepository;
import com.java.library.service.GenreService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public void deleteGenre(Long id) {
        genreRepository.delete(findById(id));
    }

    @Override
    public Set<Genre> addGenresToBook(BookDTO bookDTO) {
        Set<Genre> genres = new HashSet<>();
        if (!bookDTO.getGenres().isEmpty()) {   // check if genres not in db create and set or just set to books
            bookDTO.getGenres().parallelStream().forEach(genreDTO ->
                    genreRepository.findByName(genreDTO.getName())
                            .ifPresentOrElse(genres::add,
                                    () -> genres.add(genreRepository.save(genreMapper.toEntity(genreDTO)))
                            ));
        }
        return genres;
    }

    private Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
