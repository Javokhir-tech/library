package com.java.library.service.impl;

import com.java.library.domain.Book;
import com.java.library.domain.Genre;
import com.java.library.dto.BookDTO;
import com.java.library.dto.GenreDTO;
import com.java.library.dto.mapper.BookMapper;
import com.java.library.dto.mapper.GenreMapper;
import com.java.library.repository.BookRepository;
import com.java.library.repository.GenreRepository;
import com.java.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<BookDTO> getAll() {
        return bookMapper.toDTOList(bookRepository.findAll());
    }

    @Override
    public BookDTO getOne(Long id) {
        return bookMapper.toDTO(bookRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        log.info("bookDTO {}", bookDTO);
        // check genre if exist in db
        Set<Genre> genres = new HashSet<>();
        if (!bookDTO.getGenres().isEmpty()) {
            bookDTO.getGenres().stream().forEach(genreDTO -> {
//                List<Genre> genresInDb = genreRepository.findByName(genreDTO.getName());
//                log.info("genresInDb {}", genresInDb);
//                if (genresInDb.isEmpty()) {
//                    genres.add(genreRepository.save(genreMapper.toEntity(genreDTO)));
//                } else {
//                    genres.addAll(genresInDb);
//                }
                genreRepository.findByName(genreDTO.getName())
                        .ifPresentOrElse(genre -> genres.add(genre),
                                () -> genres.add(genreRepository.save(genreMapper.toEntity(genreDTO)))
                        );
            });
        }
        log.info("genres {}", genres);
        Book book = bookRepository.save(bookMapper.toEntity(bookDTO));
        book.setGenres(genres);
        log.info("book {}", book);
        return bookMapper.toDTO(book);
    }

    @Override
    public Void deleteBook(Long id) {
        bookRepository.deleteById(id);
        return null;
    }
}
