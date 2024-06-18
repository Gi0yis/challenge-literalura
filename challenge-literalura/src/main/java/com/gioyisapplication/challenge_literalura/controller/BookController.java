package com.gioyisapplication.challenge_literalura.controller;

import com.gioyisapplication.challenge_literalura.model.AuthorDetails;
import com.gioyisapplication.challenge_literalura.model.Book;
import com.gioyisapplication.challenge_literalura.repository.BookRepository;
import com.gioyisapplication.challenge_literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    public BookService bookService;

}
