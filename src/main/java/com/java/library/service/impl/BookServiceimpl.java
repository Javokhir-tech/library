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

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceimpl implements BookService {

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

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        log.info("bookDTO {}", bookDTO);
        Book book = bookRepository.save(bookMapper.toEntity(bookDTO));
        List<Genre> genres = genreRepository.saveAll(genreMapper.toEntityList(bookDTO.getGenres())); // save genres if not exist
        genres.forEach(genre -> genre.setBook(book));   // set book

        log.info("book {}", book);
        return bookMapper.toDTO(book);
    }

    @Override
    public Void deleteBook(Long id) {
        bookRepository.deleteById(id);
        return null;
    }
}
