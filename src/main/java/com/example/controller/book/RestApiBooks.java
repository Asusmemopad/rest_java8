package com.example.controller.book;

import com.example.model.Book;
import com.example.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class RestApiBooks {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book/", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> listAllBooks() {
        List<Book> books = (List<Book>) bookService.findAllBooks();
        if (books.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBook(@PathVariable("id") int id) {

        if (bookService.findById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Book book = bookService.findById(id);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/genre/{genre}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookByGenre(@PathVariable("genre") String genre) {
        return new ResponseEntity<Long>(bookService.calculateBooksByGenre(genre), HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
        if (bookService.isBookExist(book)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        bookService.saveBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<?>
        updateBook(@PathVariable("id") int id, @RequestBody Book book) {

        Book currentBook = bookService.findById(id);

        if (currentBook == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentBook.setName(book.getName());
        currentBook.setPublisched(book.getPublisched());
        currentBook.setGenre(book.getGenre());
        currentBook.setRating(book.getRating());

        bookService.updateBook(currentBook);
        return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<?> deleteBook(@PathVariable("id") int id) {

        if (bookService.findById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBookById(id);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/book/", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<Book> deleteAllBooks() {
        bookService.deleteAllBooks();
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

}
