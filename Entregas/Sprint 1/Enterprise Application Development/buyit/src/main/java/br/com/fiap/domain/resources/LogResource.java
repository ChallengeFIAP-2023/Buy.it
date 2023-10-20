package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Log;
import br.com.fiap.domain.service.LogService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/log")
public class LogResource {

    private final LogService service = LogService.build();
    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Log> avaliacoes = service.findAll();
        return Response.ok(avaliacoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Log log = service.findById(id);
        if (Objects.isNull(log)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(log).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Log log) {
        if (log == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Log persistedLog = service.persist(log);
        URI location = URI.create("/log/" + persistedLog.getId_log());
        return Response.created(location).entity(persistedLog).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Log log) {
        Log existingLog = service.findById(id);
        if (existingLog == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        log.setId_log(existingLog.getId_log());
        Log updatedLog = service.update(log);
        return Response.ok(updatedLog).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Log log = service.findById(id);
        if (log == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(log);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
