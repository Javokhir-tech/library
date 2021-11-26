package com.java.library.domain;

import com.java.library.domain.generic.Model;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
