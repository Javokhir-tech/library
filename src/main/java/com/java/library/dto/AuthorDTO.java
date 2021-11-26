package com.java.library.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AuthorDTO {

    @NotBlank
    private String fullName;

    private String country;

    private String sex;

//    private List<BookDTO> books;  if it's used here stackoverflow happen in mapperImpl
}
