package com.java.library.domain;

import com.java.library.domain.generic.Model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@Data
public class Author extends Model {

    private String full_name;

    private String country;

    private String sex;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}
