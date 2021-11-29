package com.java.library.domain;

import com.java.library.domain.generic.Model;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedEntityGraph(
        name = "Author.books.genres",
        attributeNodes = {
            @NamedAttributeNode(value = "books", subgraph = "books-sub")
        },
        subgraphs = {
            @NamedSubgraph(
                    name = "books-sub",
                    attributeNodes = {
                            @NamedAttributeNode(value = "genres", subgraph = "genres-sub")
                    }),
            @NamedSubgraph(
                    name = "genres-sub",
                    attributeNodes = {
                            @NamedAttributeNode("name")
                    }
            )
        }
)
@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Author extends Model {

    private String fullName;

    private String country;

    private String sex;

    @ManyToMany(mappedBy = "authors")
    @ToString.Exclude
    private Set<Book> books = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Author author = (Author) o;

        return Objects.equals(getId(), author.getId());
    }

    @Override
    public int hashCode() {
        return 556590234;
    }
}
