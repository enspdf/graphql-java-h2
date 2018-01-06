package me.shackox.h2.graphql.h2graphqljpa.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import me.shackox.h2.graphql.h2graphqljpa.model.Author;
import me.shackox.h2.graphql.h2graphqljpa.model.Book;
import me.shackox.h2.graphql.h2graphqljpa.repository.AuthorRepository;
import me.shackox.h2.graphql.h2graphqljpa.repository.BookRepository;

public class Query implements GraphQLQueryResolver {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Query(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Iterable<Book> findAllBooks () {
        return bookRepository.findAll();
    }

    public Iterable<Author> findAllAuthors () {
        return authorRepository.findAll();
    }

    public long countBooks () {
        return bookRepository.count();
    }

    public long countAuthors () {
        return authorRepository.count();
    }
}
