package com.java.library.domain;

import com.java.library.domain.generic.Model;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genres")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Genre extends Model {

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "genres")
    @ToString.Exclude
    private Set<Book> books = new HashSet<>();

    public void setBook(Book book) {
        if (book != null)
            this.books.add(book);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Genre genre = (Genre) o;
//
//        return Objects.equals(getId(), genre.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return 1887069089;
//    }
}
