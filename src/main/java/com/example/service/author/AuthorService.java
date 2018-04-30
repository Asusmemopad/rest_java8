package com.example.service.author;

import com.example.model.Author;
import com.example.model.AuthorBook;
import com.example.model.Book;

import java.util.Collection;
import java.util.Set;

public interface AuthorService {
    public Collection<Author> findAllAuthor();
    public Author findById(int id);
    public boolean isAuthorExist(Author author);
    public void saveAuthor(Author author);
    public void updateAuthor(Author author);
    public void deleteAuthorById(int id);
    public void deleteAllAuthors();
    public Collection<AuthorBook> getJoinAuthorsBooks();
    public Collection<Author> yearsAuthor(int years);
    public Set<Author> writeOneBook();
    public Collection<Book> writeAuthor(int id);
}
