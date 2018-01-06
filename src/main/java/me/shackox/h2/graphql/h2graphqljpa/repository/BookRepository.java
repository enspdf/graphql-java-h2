package me.shackox.h2.graphql.h2graphqljpa.repository;

import me.shackox.h2.graphql.h2graphqljpa.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
