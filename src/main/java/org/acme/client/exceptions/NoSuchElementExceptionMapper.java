package org.acme.client.exceptions;

import java.util.NoSuchElementException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NoSuchElementExceptionMapper implements ExceptionMapper<NoSuchElementException> {
    public static record NoSuchElementMessage(String message){}

    @Override
    public Response toResponse(NoSuchElementException exception) {
        var error = new NoSuchElementMessage(exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
