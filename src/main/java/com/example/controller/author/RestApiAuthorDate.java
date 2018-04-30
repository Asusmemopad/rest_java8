package com.example.controller.author;

import com.example.model.Author;
import com.example.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeSet;

@RestController
public class RestApiAuthorDate {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/author/date/{years}", method = RequestMethod.GET)
    public ResponseEntity<TreeSet<Author>> listAllAuthorsDate(@PathVariable("years") int years) {
        TreeSet<Author> authors = (TreeSet<Author>) authorService.yearsAuthor(years);
        if (authors.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<TreeSet<Author>>(authors, HttpStatus.OK);
    }
}
