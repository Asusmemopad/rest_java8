package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "author_book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AuthorBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotFound(action = NotFoundAction.IGNORE)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book_id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author_id;

    public AuthorBook() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook_id() {
        return book_id;
    }

    public void setBook_id(Book book_id) {
        this.book_id = book_id;
    }

    public Author getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Author author_id) {
        this.author_id = author_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorBook that = (AuthorBook) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (book_id != null ? !book_id.equals(that.book_id) : that.book_id != null) return false;
        return author_id != null ? author_id.equals(that.author_id) : that.author_id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (book_id != null ? book_id.hashCode() : 0);
        result = 31 * result + (author_id != null ? author_id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuthorBook{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", author_id=" + author_id +
                '}';
    }
}
