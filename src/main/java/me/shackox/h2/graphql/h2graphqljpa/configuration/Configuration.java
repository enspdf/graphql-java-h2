package me.shackox.h2.graphql.h2graphqljpa.configuration;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import me.shackox.h2.graphql.h2graphqljpa.exceptions.GraphQLErrorAdapter;
import me.shackox.h2.graphql.h2graphqljpa.model.Book;
import me.shackox.h2.graphql.h2graphqljpa.repository.AuthorRepository;
import me.shackox.h2.graphql.h2graphqljpa.repository.BookRepository;
import me.shackox.h2.graphql.h2graphqljpa.resolver.BookResolver;
import me.shackox.h2.graphql.h2graphqljpa.resolver.Mutation;
import me.shackox.h2.graphql.h2graphqljpa.resolver.Query;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public GraphQLErrorHandler errorHandler () {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientError = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientError);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError (GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public BookResolver authorResolver (AuthorRepository authorRepository) {
        return new BookResolver(authorRepository);
    }

    @Bean
    public Query query (AuthorRepository authorRepository, BookRepository bookRepository) {
        return new Query(bookRepository, authorRepository);
    }

    @Bean
    public Mutation mutation (AuthorRepository authorRepository, BookRepository bookRepository) {
        return new Mutation(authorRepository, bookRepository);
    }
}
