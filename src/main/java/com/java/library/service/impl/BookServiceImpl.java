package com.java.library.service.impl;

import com.java.library.domain.Book;
import com.java.library.domain.Genre;
import com.java.library.dto.BookDTO;
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
        Set<Genre> genres = new HashSet<>();
        if (!bookDTO.getGenres().isEmpty()) {   // check if genres not in db create and set or just set to books
            bookDTO.getGenres().stream().forEach(genreDTO -> {
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
    public void deleteBook(Long id) {
        if (bookRepository.existsById(id))
            bookRepository.deleteById(id);
        else
            throw new EntityNotFoundException();
    }
}
