package com.java.library.domain;


import com.java.library.domain.generic.Model;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User extends Model {

    private String firstName;

    private String lastName;

    private Integer age;

    private String address;

    @ManyToMany
    @JoinTable(
            name = "user_books",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_id"))},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_book_id"))}
    )
    private Set<Book> books = new HashSet<>();
}
