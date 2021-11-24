package com.java.library.domain;

import com.java.library.domain.generic.Model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@Data
public class Genre extends Model {

    private String name;

    @ManyToOne
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "fk_genre_book_id"))
    private Book book;
}
