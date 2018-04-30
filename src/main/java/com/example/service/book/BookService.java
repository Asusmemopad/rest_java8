package com.example.service.book;

import com.example.model.Book;

import java.util.Collection;

public interface BookService {
    public Collection<Book> findAllBooks();
    public Book findById(int id);
    public boolean isBookExist(Book book);
    public void saveBook(Book book);
    public void updateBook(Book book);
    public void deleteBookById(int id);
    public void deleteAllBooks();
    public Long calculateBooksByGenre(String genre);
}
