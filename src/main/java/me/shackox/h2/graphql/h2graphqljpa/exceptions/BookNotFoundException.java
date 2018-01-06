package me.shackox.h2.graphql.h2graphqljpa.exceptions;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookNotFoundException extends RuntimeException implements GraphQLError {
    private Map<String, Object> extension = new HashMap<String, Object>();

    public BookNotFoundException (String message, Long invalidBookId) {
        super(message);
        extension.put("invalidBookId", invalidBookId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extension;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
