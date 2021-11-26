package com.java.library.dto.mapper;

import com.java.library.domain.Author;
import com.java.library.dto.AuthorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "updatedAt", expression = "java(java.time.Instant.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);

    List<AuthorDTO> toDTOList(List<Author> authors);
}
