package com.java.library.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class BookDTO {

    @NotBlank
    private String name;

    private Integer price;

    private Integer year;

    @NotBlank
    private List<GenreDTO> genres;

    private List<AuthorDTO> authors;
}
