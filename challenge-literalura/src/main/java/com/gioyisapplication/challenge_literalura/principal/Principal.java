package com.gioyisapplication.challenge_literalura.principal;

import com.gioyisapplication.challenge_literalura.model.Author;
import com.gioyisapplication.challenge_literalura.model.Book;
import com.gioyisapplication.challenge_literalura.model.Data;
import com.gioyisapplication.challenge_literalura.repository.AuthorDetailsRepository;
import com.gioyisapplication.challenge_literalura.repository.BookRepository;
import com.gioyisapplication.challenge_literalura.service.AuthorService;
import com.gioyisapplication.challenge_literalura.service.BookService;
import com.gioyisapplication.challenge_literalura.service.ConsumeAPI;
import com.gioyisapplication.challenge_literalura.service.ConvertData;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    // http://gutendex.com/books/?page=2
    private Scanner key = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private ConvertData convertData = new ConvertData();
    private final String URL_BASE = "http://gutendex.com/books/?search=";

    @Autowired
    private BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorDetailsRepository authorDetailsRepository;

    public Principal(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void showMenu() {
        var option = -1;
        while (option != 0) {

            var menu = """
                1 - Buscar libro por titulo
                2 - Mostrar libros registrados
                3 - Mostrar autores registrados
                4 - Buscar actores vivos en determinado año
                5 - listar libros por idioma
                0 - Salir
            """;
            System.out.println("-".repeat(50));

            System.out.println("\n" +menu);

            System.out.println("-".repeat(50));

            try {
                option = key.nextInt();
                key.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                key.nextLine();
            }


            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    getSavedBooks();
                    break;
                case 3:
                    getSavedAuthors();
                    break;
                case 4:
                    searchAuthorsByYear();
                    break;
                case 5:
                    getSavedlanguage();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción no valida, ingrese una de las opciones disponibles");
                }
            }
    }

    private void searchBookByTitle() {

        try {
            System.out.println("Ingrese el nombre del libro:");
            var titleBook = key.nextLine();

            var json = consumeAPI.getData(URL_BASE + titleBook.replace(" ", "+"));

            var serchData = convertData.getData(json, Data.class);

            Optional<Book> bookOptional = serchData.results().stream()
                    .filter(b -> b.getTitle().toUpperCase().contains(titleBook.toUpperCase()))
                    .findFirst();

            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                bookRepository.save(book);
                System.out.println("Libro encontrado y guardado:");
                System.out.println(book);
            } else {
                System.out.println("Libro no encotrado");
            }
        } catch (DataIntegrityViolationException e){
            System.out.println("No se pueden ingresar libros repetidos");
        }

    }

    private void getSavedBooks() {
        List<Object[]> books = bookRepository.getSavedBooks();
        for (Object[] book : books) {
            String title = (String) book[0];
            String authorName = (String) book[3];
            String languages = (String) book[1];
            Integer downloadCount = (Integer) book[2];

            System.out.println("-".repeat(50));
            System.out.println("Titulo: " + title);
            System.out.println("Autor: " + authorName);
            System.out.println("Lenguaje: " + languages);
            System.out.println("Descargas: " + downloadCount);
            System.out.println("-".repeat(50));
        }
    }

    private void getSavedAuthors() {
        List<Object[]> getAllAuthors = bookRepository.getSavedAuthors();
        for (Object[] author : getAllAuthors) {
            String name = (String) author[0];
            Integer birthYear = (Integer) author[1];
            Integer deathYear = (Integer) author[2];

            System.out.println("-".repeat(50));
            System.out.println("Nombre: " + name);
            System.out.println("Fecha de nacimiento: " + birthYear);
            System.out.println("Fecha de muerte: " + deathYear);
            System.out.println("-".repeat(50));

        }
    }

    private void searchAuthorsByYear() {
        try {
            System.out.println("Ingrese el año:");
            int year = key.nextInt();
            key.nextLine();

            List<Object[]> authors = bookRepository.searchAuthorsByYear(year);
            for (Object[] author : authors) {
                String name = (String) author[0];
                Integer birthYear = (Integer) author[1];
                Integer deathYear = (Integer) author[2];
                String title = (String) author[3];

                System.out.println("-".repeat(50));
                System.out.println("Nombre: " + name);
                System.out.println("Fecha de nacimiento: " + birthYear);
                System.out.println("fecha de muerte: " + deathYear);
                System.out.println("Libros: " + title);
                System.out.println("-".repeat(50));
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            key.nextLine();
        }
    }

    private void getSavedlanguage() {
        String language = "";
        var menu = """
                1 - Español
                2 - Ingles
            """;

        System.out.println("-".repeat(50));
        System.out.println("\n" + menu);
        System.out.println("-".repeat(50));

        int opcion = key.nextInt();
        switch (opcion) {
            case 1:
                language = "es";
                break;
            case 2:
                language = "en";
                break;
            default:
                System.out.println("Error");
                break;
        }


        List<Object[]> books = bookRepository.getSavedLanguage(language);
        for (Object[] book : books) {
            String title = (String) book[0];
            String authorName = (String) book[3];
            String languages = (String) book[1];
            Integer downloadCount = (Integer) book[2];

            System.out.println("-".repeat(65));
            System.out.println("Titulo: " + title);
            System.out.println("Autor: " + authorName);
            System.out.println("Lenguaje: " + languages);
            System.out.println("Descargas: " + downloadCount);
            System.out.println("-".repeat(65));
        }
    }
}
