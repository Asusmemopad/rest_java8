package com.example.service.authorBook;

import com.example.model.Author;
import com.example.model.AuthorBook;
import com.example.repositories.AuthorBookRepository;

import java.util.Collection;
import java.util.Set;

public interface AuthorBookService {
    public Collection<AuthorBook> findAllJoinAuthorsBooks();
    public AuthorBook findByIdJoinAuthorsBooks(int id);
    public boolean isJoinAuthorBookExist(AuthorBook authorBook);
    public void saveJoinAuthorBook(AuthorBook authorBook);
    public void updateJoinAuthorBook(AuthorBook authorBook);
    public void deleteJoinAuthorBookById(int id);
    public void deleteAllJoinAuthorsBooks();
}
