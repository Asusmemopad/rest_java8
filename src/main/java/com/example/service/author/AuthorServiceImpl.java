package com.example.service.author;

import com.example.model.Author;
import com.example.model.AuthorBook;
import com.example.model.Book;
import com.example.repositories.AuthorRepository;
import com.example.service.authorBook.AuthorBookService;
import com.example.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorBookService authorBookService;

    @Autowired
    private BookService bookService;

    @Override
    public Collection<Author> findAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(int id) {
        try {
            return authorRepository.findById(id).get();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public boolean isAuthorExist(Author author) {
        return authorRepository.existsById(author.getId());
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Author author) {
        saveAuthor(author);
    }

    @Override
    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void deleteAllAuthors() {
        authorRepository.deleteAll();
    }

    @Override
    public Collection<AuthorBook> getJoinAuthorsBooks() {
        return authorBookService.findAllJoinAuthorsBooks();
    }

    @Override
    public Set<Author> yearsAuthor(int years) {
        return authorRepository.findAll().stream().
                filter(a -> (a.getBorn() != null) &&
                        (LocalDate.now().getYear() - a.getBorn().getYear()) > years).
                            collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public Set<Author> writeOneBook() {
        return getJoinAuthorsBooks().stream().
                map(a -> findById(a.getAuthor_id().getId())).collect(Collectors.toSet());
    }

    @Override
    public Collection<Book> writeAuthor(int id) {
        return  getJoinAuthorsBooks().stream().filter(b -> b.getAuthor_id().getId() == id).
                    map(AuthorBook::getBook_id).collect(Collectors.toList());
    }
}
