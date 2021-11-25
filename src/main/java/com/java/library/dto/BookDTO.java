package com.java.library.dto;

import lombok.Data;

import java.util.List;


@Data
public class BookDTO {

    private String name;

    private Integer price;

    private Integer year;

    private List<GenreDTO> genres;

    private List<AuthorDTO> authors;
}
