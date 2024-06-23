package com.gioyisapplication.challenge_literalura.repository;

import com.gioyisapplication.challenge_literalura.model.Author;
import com.gioyisapplication.challenge_literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBytitleContainsIgnoreCase(String nombreBook);

    @Query("SELECT b.title, b.languages, b.downloadCount, a.name FROM Book b JOIN b.authors a")
    List<Object[]> getSavedBooks();

    @Query("SELECT a.name, a.birthYear, a.deathYear FROM Book b JOIN b.authors a")
    List<Object[]> getSavedAuthors();

    @Query("SELECT a.name, a.birthYear, a.deathYear, b.title FROM Book b JOIN b.authors a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Object[]> searchAuthorsByYear(@Param("year") int year);

    @Query("SELECT b.title, b.languages, b.downloadCount, a.name FROM Book b JOIN b.authors a WHERE :language MEMBER OF b.languages")
    List<Object[]> getSavedLanguage(@Param("language") String language);
    // @Query("SELECT a.name FROM Book b JOIN b.authors a ON a.title")
   // List<Object[]> authorsLiveByYear(Integer year);

}
