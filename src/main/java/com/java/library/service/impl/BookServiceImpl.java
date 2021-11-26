package com.java.library.service.impl;

import com.java.library.domain.Author;
import com.java.library.domain.Book;
import com.java.library.domain.Genre;
import com.java.library.dto.BookDTO;
import com.java.library.dto.mapper.AuthorMapper;
import com.java.library.dto.mapper.BookMapper;
import com.java.library.dto.mapper.GenreMapper;
import com.java.library.repository.AuthorRepository;
import com.java.library.repository.BookRepository;
import com.java.library.repository.GenreRepository;
import com.java.library.service.AuthorService;
import com.java.library.service.BookService;
import com.java.library.service.GenreService;
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
    private final GenreService genreService;
//    private final GenreRepository genreRepository;
//    private final GenreMapper genreMapper;
    private final AuthorService authorService;

    @Override
    public List<BookDTO> getAll() {
        return bookMapper.toDTOList(bookRepository.findAll());
    }

    @Override
    public BookDTO getOne(Long id) {
        return bookMapper.toDTO(getBookById(id));
    }

    @Transactional
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        log.info("bookDTO {}", bookDTO);
//        Set<Genre> genres = new HashSet<>();
//        Set<Author> authors = new HashSet<>();
        Set<Genre> genres = genreService.addGenresToBook(bookDTO);
//        if (!bookDTO.getGenres().isEmpty()) {   // check if genres not in db create and set or just set to books
//            bookDTO.getGenres().parallelStream().forEach(genreDTO ->
//                    genreRepository.findByName(genreDTO.getName())
//                            .ifPresentOrElse(genres::add,
//                                    () -> genres.add(genreRepository.save(genreMapper.toEntity(genreDTO)))
//                    ));
//        }
        Set<Author> authors = authorService.addAuthorsToBooks(bookDTO);
//        if (!bookDTO.getAuthors().isEmpty()) {
//            bookDTO.getAuthors().parallelStream().forEach(authorDTO ->
//                    authorRepository.findByFullName(authorDTO.getFull_name())
//                            .ifPresentOrElse(authors::add,
//                                    () -> authors.add(authorRepository.save(authorMapper.toEntity(authorDTO))))
//                    );
//        }
        log.info("genres {},\n authors {}", genres, authors);
        Book book = bookRepository.save(bookMapper.toEntity(bookDTO));
        book.setGenres(genres);
        book.setAuthors(authors);
        log.info("book {}", book);
        return bookMapper.toDTO(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(getBookById(id));
    }

    private Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
