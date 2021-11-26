package com.java.library.repository;

import com.java.library.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void TestGetByName() {

        Optional<Genre> genre = genreRepository.findByName("fantasy");
        assertTrue(genre.isEmpty());
//        genre.ifPresent(entity -> {
//            assertEquals("fantasy", genre.get(0).getName());
//        });
    }
}