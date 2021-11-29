package com.java.library.domain;

import com.java.library.domain.generic.Model;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NamedEntityGraph(
    name = "Book.genres",
    attributeNodes = {
        @NamedAttributeNode("name"),
        @NamedAttributeNode("year"),
        @NamedAttributeNode(value = "genres", subgraph = "genres-sub")
    },
    subgraphs = {
       @NamedSubgraph(
               name = "genres-sub",
               attributeNodes ={
                       @NamedAttributeNode("name")
               }
       )
    }
)
public class Book extends Model {

    private String name;

    private Integer year;

    @ManyToMany
    @JoinTable(
            name = "author_books",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_book_id"))},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_author_id"))}
    )
    @ToString.Exclude
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(/*cascade = {PERSIST, MERGE , REFRESH, DETACH}*/)
    @JoinTable(
            name = "genre_books",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_book_id"))},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_genre_id"))}
    )
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();

    private Integer price;

    @ManyToMany(mappedBy = "books")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    public void addGenre(Genre genre) {
        if (genre != null)
            this.genres.add(genre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;

        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return 967762358;
    }
}