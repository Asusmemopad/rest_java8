package com.example.service.authorBook;

import com.example.model.AuthorBook;
import com.example.repositories.AuthorBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class AuthorBookServiceImpl implements AuthorBookService {

    @Autowired
    private AuthorBookRepository authorBookRepository;

    @Override
    public Collection<AuthorBook> findAllJoinAuthorsBooks() {
        return authorBookRepository.findAll();
    }

    @Override
    public AuthorBook findByIdJoinAuthorsBooks(int id) {
        try{
            return authorBookRepository.findById(id).get();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public boolean isJoinAuthorBookExist(AuthorBook authorBook) {
        return authorBookRepository.existsById(authorBook.getId());
    }

    @Override
    public void saveJoinAuthorBook(AuthorBook authorBook) {
        authorBookRepository.save(authorBook);
    }

    @Override
    public void updateJoinAuthorBook(AuthorBook authorBook) {
        saveJoinAuthorBook(authorBook);
    }

    @Override
    public void deleteJoinAuthorBookById(int id) {
        authorBookRepository.deleteById(id);
    }

    @Override
    public void deleteAllJoinAuthorsBooks() {
        authorBookRepository.deleteAll();
    }
}
