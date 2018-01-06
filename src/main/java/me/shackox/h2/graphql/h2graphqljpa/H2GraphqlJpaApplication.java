package me.shackox.h2.graphql.h2graphqljpa;

import me.shackox.h2.graphql.h2graphqljpa.model.Author;
import me.shackox.h2.graphql.h2graphqljpa.model.Book;
import me.shackox.h2.graphql.h2graphqljpa.repository.AuthorRepository;
import me.shackox.h2.graphql.h2graphqljpa.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class H2GraphqlJpaApplication {

	@Bean
	public CommandLineRunner demo (AuthorRepository authorRepository, BookRepository bookRepository) {
		return args -> {
			Author author = new Author("Herbert", "Schildt");
			authorRepository.save(author);

			bookRepository.save(new Book("A Book", "0078182714", 728, author));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(H2GraphqlJpaApplication.class, args);
	}
}
