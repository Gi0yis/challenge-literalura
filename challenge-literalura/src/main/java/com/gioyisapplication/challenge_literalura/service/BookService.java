package com.gioyisapplication.challenge_literalura.service;

import com.gioyisapplication.challenge_literalura.model.Book;
import com.gioyisapplication.challenge_literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
