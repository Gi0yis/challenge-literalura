package com.gioyisapplication.challenge_literalura.principal;

import com.gioyisapplication.challenge_literalura.service.ConsumeAPI;

import java.util.Scanner;

public class Principal {
    // http://gutendex.com/books/?page=2
    Scanner key = new Scanner(System.in);
    ConsumeAPI consumeAPI = new ConsumeAPI();
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
                    System.out.println("Cerrando de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no valida, ingrese una de las opciones disponibles");
            }
        }
    }

    private void searchBookByTitle() {
        System.out.println("Ingrse el nombre del libro:");
        var titleBook = key.nextLine();
        consumeAPI.getData(URL_BASE + titleBook.replace(" ", "+"));
    }

}
