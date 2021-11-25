package com.java.library.dto;

import lombok.Data;
import java.util.List;

@Data
public class AuthorDTO {

    private String full_name;

    private String country;

    private String sex;

    private List<BookDTO> books;
}
