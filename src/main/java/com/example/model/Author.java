package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "authors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author implements Comparable<Author>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotFound(action = NotFoundAction.IGNORE)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "born")
    private LocalDate born;

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (id != null ? !id.equals(author.id) : author.id != null) return false;
        if (name != null ? !name.equals(author.name) : author.name != null) return false;
        if (genre != null ? !genre.equals(author.genre) : author.genre != null) return false;
        return born != null ? born.equals(author.born) : author.born == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (born != null ? born.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", born='" + born + '\'' +
                '}';
    }

    @Override
    public int compareTo(Author o) {
        return this.born.getYear() - o.born.getYear();
    }
}
