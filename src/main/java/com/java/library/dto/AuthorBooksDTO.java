package com.java.library.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AuthorBooksDTO {

    @NotBlank
    private String fullName;

    private List<BookNameDTO> books;
}
