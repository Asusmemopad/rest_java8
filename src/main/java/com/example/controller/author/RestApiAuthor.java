package com.example.controller.author;

import com.example.model.Author;
import com.example.model.Book;
import com.example.service.author.AuthorService;
import com.example.service.authorBook.AuthorBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
public class RestApiAuthor {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorBookService authorBookService;

    @RequestMapping(value = "/author/", method = RequestMethod.GET)
    public ResponseEntity<List<Author>> listAllAuthors() {
        List<Author> authors = (List<Author>) authorService.findAllAuthor();
        if (authors.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }

    @RequestMapping(value = "/author/book/", method = RequestMethod.GET)
    public ResponseEntity<Set<Author>> listAuthorsWriteBook() {
        Set<Author> authors = authorService.writeOneBook();
        if (authors.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Author>>(authors, HttpStatus.OK);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthor(@PathVariable("id") int id) {

        if (authorService.findById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Author author = authorService.findById(id);
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @RequestMapping(value = "/author/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthorBook(@PathVariable("id") int id) {

        if (authorService.findById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Book>>(authorService.writeAuthor(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> createBook(@RequestBody Author author,
                                        UriComponentsBuilder ucBuilder) {
        if (authorService.isAuthorExist(author)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        authorService.saveAuthor(author);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/author/{id}").buildAndExpand(author.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<?>
        updateUser(@PathVariable("id") int id, @RequestBody Author author) {

        Author currentAuthor = authorService.findById(id);

        if (currentAuthor == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentAuthor.setName(author.getName());
        currentAuthor.setGenre(author.getGenre());
        currentAuthor.setBorn(author.getBorn());

        authorService.updateAuthor(currentAuthor);
        return new ResponseEntity<Author>(currentAuthor, HttpStatus.OK);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<?> deleteUser(@PathVariable("id") int id) {

        if (authorService.findById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        authorService.deleteAuthorById(id);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/author/", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<Author> deleteAllAuthors() {
        authorService.deleteAllAuthors();

        return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
    }
}
