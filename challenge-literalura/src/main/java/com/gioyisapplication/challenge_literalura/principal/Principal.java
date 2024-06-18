package com.gioyisapplication.challenge_literalura.principal;

import com.gioyisapplication.challenge_literalura.model.Book;
import com.gioyisapplication.challenge_literalura.model.Data;
import com.gioyisapplication.challenge_literalura.service.ConsumeAPI;
import com.gioyisapplication.challenge_literalura.service.ConvertData;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    // http://gutendex.com/books/?page=2
    private Scanner key = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private ConvertData convertData = new ConvertData();
    private final String URL_BASE = "http://gutendex.com/books/?search=";

    public void showMenu() {
        var option = -1;
        while (option != 0) {

            var menu = """
                    1 - Buscar libro por titulo
                    0 - Salir
                    """;
            System.out.println(menu);
            option = key.nextInt();
            key.nextLine();


            switch (option) {
                case 1:
                    searchBookByTitle();
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
        System.out.println("Ingrese el nombre del libro:");
        var titleBook = key.nextLine();

        var json = consumeAPI.getData(URL_BASE + titleBook.replace(" ", "+"));

        var serchData = convertData.getData(json, Data.class);

        Optional<Book> bookOptional = serchData.results().stream()
                .filter(b -> b.getTitle().toUpperCase().contains(titleBook.toUpperCase()))
                .findFirst();

        if (bookOptional.isPresent()) {
            System.out.println("Libro encontrado:");
            System.out.println(bookOptional.get());
        } else {
            System.out.println("Libro no encotrado");
        }

    }

}
