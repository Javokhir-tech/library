package com.java.library.domain;

import com.java.library.domain.generic.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "books")
@Data
public class Book extends Model {

    private String name;

    private Integer year;

    @ManyToMany
    @JoinTable(
            name = "author_books",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_book_id"))},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_author_id"))}
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(/*cascade = {PERSIST, MERGE , REFRESH, DETACH}*/)
    @JoinTable(
            name = "genre_books",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_book_id"))},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_genre_id"))}
    )
    private Set<Genre> genres = new HashSet<>();

    private Integer price;

    @ManyToMany(mappedBy = "books")
    private Set<User> users = new HashSet<>();

    public void addGenre(Genre genre) {
        if (genre != null)
            this.genres.add(genre);
    }
}