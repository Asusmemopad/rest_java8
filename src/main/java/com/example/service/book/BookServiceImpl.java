package com.example.service.book;

import com.example.model.Book;
import com.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Collection<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        try{
            return bookRepository.findById(id).get();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public boolean isBookExist(Book book) {
        return bookRepository.existsById(book.getId());
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Book book) {
        saveBook(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    @Override
    public Long calculateBooksByGenre(String genre) {
        return findAllBooks().stream().filter(b -> b.getGenre().equals(genre)).count();
    }
}
