package me.shackox.h2.graphql.h2graphqljpa.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import me.shackox.h2.graphql.h2graphqljpa.model.Author;
import me.shackox.h2.graphql.h2graphqljpa.model.Book;
import me.shackox.h2.graphql.h2graphqljpa.repository.AuthorRepository;

public class BookResolver implements GraphQLResolver<Book> {
    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor (Book book) {
        return authorRepository.findOne(book.getAuthor().getId());
    }
}
