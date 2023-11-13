package br.com.fiap.infra;

import jakarta.ws.rs.core.Response;

public class CustomErrorResponse {
    private int error;
    private String message;

    public CustomErrorResponse() {
    }

    public CustomErrorResponse(int error, String message) {
        this.error = error;
        this.message = message;
    }

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Response createErrorResponse(Response.Status status, String message) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(status.getStatusCode(), message);
        return Response.status(status).entity(errorResponse).build();
    }
}