package com.example.controller.authorBook;

import com.example.model.AuthorBook;
import com.example.model.Book;
import com.example.service.authorBook.AuthorBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class RestApiAuthorBook {

    @Autowired
    private AuthorBookService authorBookService;

    @RequestMapping(value = "/authorBook/", method = RequestMethod.GET)
    public ResponseEntity<List<AuthorBook>> listAllAuthorsBooks() {
        List<AuthorBook> authorBooks =
                (List<AuthorBook>) authorBookService.findAllJoinAuthorsBooks();
        if (authorBooks.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<AuthorBook>>(authorBooks, HttpStatus.OK);
    }

    @RequestMapping(value = "/authorBook/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthorBook(@PathVariable("id") int id) {

        if (authorBookService.findByIdJoinAuthorsBooks(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        AuthorBook authorBook = authorBookService.findByIdJoinAuthorsBooks(id);
        return new ResponseEntity<AuthorBook>(authorBook, HttpStatus.OK);
    }

    @RequestMapping(value = "/authorBook/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthorBook(@RequestBody AuthorBook authorBook,
                                        UriComponentsBuilder ucBuilder) {
        if (authorBookService.isJoinAuthorBookExist(authorBook)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        authorBookService.saveJoinAuthorBook(authorBook);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(authorBook.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/authorBook/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<?>
        updateAuthorBook(@PathVariable("id") int id, @RequestBody AuthorBook authorBook) {

        AuthorBook currentAuthorBook = authorBookService.findByIdJoinAuthorsBooks(id);

        if (currentAuthorBook == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentAuthorBook.setBook_id(authorBook.getBook_id());
        currentAuthorBook.setAuthor_id(authorBook.getAuthor_id());

        authorBookService.updateJoinAuthorBook(currentAuthorBook);
        return new ResponseEntity<AuthorBook>(currentAuthorBook, HttpStatus.OK);
    }

    @RequestMapping(value = "/authorBook/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<?> deleteAuthorBook(@PathVariable("id") int id) {

        if (authorBookService.findByIdJoinAuthorsBooks(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        authorBookService.deleteJoinAuthorBookById(id);
        return new ResponseEntity<AuthorBook>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/authorBook/", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<AuthorBook> deleteAllAuthorsBooks() {
        authorBookService.deleteAllJoinAuthorsBooks();
        return new ResponseEntity<AuthorBook>(HttpStatus.NO_CONTENT);
    }
}
