package com.java.library.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GenreDTO {

    @NotBlank
    private String name;
}
