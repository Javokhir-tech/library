package com.java.library.dto.mapper;

import com.java.library.domain.Book;
import com.java.library.dto.BookDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

//    @Named("book")
    @Mapping(target = "updatedAt", expression = "java(java.time.Instant.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    Book toEntity(BookDTO bookDTO);

    BookDTO toDTO(Book book);

//    @IterableMapping(qualifiedByName = "book")
    List<BookDTO> toDTOList(List<Book> books);
}
