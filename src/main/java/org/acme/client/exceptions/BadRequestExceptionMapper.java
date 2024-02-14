package org.acme.client.exceptions;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {
    public static record BadRequestElementMessage(String message){}

    @Override
    public Response toResponse(BadRequestException exception) {
        var error = new BadRequestElementMessage(exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
