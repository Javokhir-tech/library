package com.java.library.service;

import com.java.library.domain.Author;
import com.java.library.dto.AuthorBooksDTO;
import com.java.library.dto.AuthorDTO;
import com.java.library.dto.BookDTO;

import java.util.List;
import java.util.Set;

public interface AuthorService {

    AuthorDTO addAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> getAllAuthors();

    AuthorDTO getAuthor(Long id);

    void deleteAuthor(Long id);

    Set<Author> addAuthorsToBooks(BookDTO bookDTO);

    AuthorBooksDTO getAuthorsAllBooks(long id);
}
