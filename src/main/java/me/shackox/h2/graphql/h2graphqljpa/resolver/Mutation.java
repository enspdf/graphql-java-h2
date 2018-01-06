package me.shackox.h2.graphql.h2graphqljpa.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import me.shackox.h2.graphql.h2graphqljpa.exceptions.BookNotFoundException;
import me.shackox.h2.graphql.h2graphqljpa.model.Author;
import me.shackox.h2.graphql.h2graphqljpa.model.Book;
import me.shackox.h2.graphql.h2graphqljpa.repository.AuthorRepository;
import me.shackox.h2.graphql.h2graphqljpa.repository.BookRepository;

public class Mutation implements GraphQLMutationResolver {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author newAuthor (String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorRepository.save(author);

        return author;
    }

    public Book newBook (String title, String isbn, Integer pageCount, Long authorId) {
        Book book = new Book();
        book.setAuthor(new Author(authorId));
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPageCount(pageCount != null ? pageCount : 0);

        bookRepository.save(book);

        return book;
    }

    public boolean deleteBook (Long id) {
        bookRepository.delete(id);
        return true;
    }

    public Book updateBookPageCount (Integer pageCount, Long id) {
        Book book = bookRepository.findOne(id);
        if (book == null) {
            throw new BookNotFoundException("The book to be updated was found", id);
        }
        book.setPageCount(pageCount);

        bookRepository.save(book);

        return book;
    }
}
