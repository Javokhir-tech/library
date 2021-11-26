package com.java.library.dto.mapper;

import com.java.library.domain.Genre;
import com.java.library.dto.GenreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "updatedAt", expression = "java(java.time.Instant.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    Genre toEntity(GenreDTO genreDTO);

    GenreDTO toDto(Genre genre);

    List<Genre> toEntityList(List<GenreDTO> genreDTOList);

    List<GenreDTO> toDTOList(List<Genre> genres);
}
