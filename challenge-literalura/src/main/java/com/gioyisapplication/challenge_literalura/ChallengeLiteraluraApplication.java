package com.gioyisapplication.challenge_literalura;

import com.gioyisapplication.challenge_literalura.principal.Principal;
import com.gioyisapplication.challenge_literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(bookRepository);
		principal.showMenu();
	}
}
