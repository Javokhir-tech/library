package com.java.library.service.impl;

import com.java.library.domain.Author;
import com.java.library.dto.AuthorDTO;
import com.java.library.dto.BookDTO;
import com.java.library.dto.mapper.AuthorMapper;
import com.java.library.repository.AuthorRepository;
import com.java.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        return authorMapper.toDTO(authorRepository.save(authorMapper.toEntity(authorDTO)));
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorMapper.toDTOList(authorRepository.findAll());
    }

    @Override
    public AuthorDTO getAuthor(Long id) {
        return authorMapper.toDTO(getAuthorById(id));
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.delete(getAuthorById(id));
    }

    private Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Set<Author> addAuthorsToBooks(BookDTO bookDTO) {
        log.info("authorRepository.findByFullName {}", authorRepository.findByFullName("J.K Rowling"));
        Set<Author> authors = new HashSet<>();
        if (!bookDTO.getAuthors().isEmpty()) {
            bookDTO.getAuthors().parallelStream().forEach(authorDTO ->
                    authorRepository.findByFullName(authorDTO.getFullName())
                            .ifPresentOrElse(authors::add,
                                    () -> authors.add(authorRepository.save(authorMapper.toEntity(authorDTO)))
                            ));
        }
        return authors;
    }
}
