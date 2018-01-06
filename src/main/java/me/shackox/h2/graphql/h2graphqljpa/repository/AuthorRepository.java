package me.shackox.h2.graphql.h2graphqljpa.repository;

import me.shackox.h2.graphql.h2graphqljpa.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
