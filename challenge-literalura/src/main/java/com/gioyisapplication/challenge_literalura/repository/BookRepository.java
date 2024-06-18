package com.gioyisapplication.challenge_literalura.repository;

import com.gioyisapplication.challenge_literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
