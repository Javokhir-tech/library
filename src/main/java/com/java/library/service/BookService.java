package com.java.library.service;

import com.java.library.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> getAll();

    BookDTO getOne(Long id);

    BookDTO createBook(BookDTO bookDTO);

    Void deleteBook(Long id);
}
