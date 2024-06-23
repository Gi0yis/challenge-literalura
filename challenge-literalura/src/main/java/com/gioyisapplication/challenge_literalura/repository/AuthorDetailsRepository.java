package com.gioyisapplication.challenge_literalura.repository;

import com.gioyisapplication.challenge_literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorDetailsRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a.name, a.birthYear, a.deathYear FROM Book b JOIN b.authors a")
    List<Object[]> getSavedAuthors();

    @Query("SELECT a.name, a.birthYear, a.deathYear, b.title " +
            "FROM Author a JOIN a.books b " +
            "WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Object[]> findAuthorsByYear(int year);
}
