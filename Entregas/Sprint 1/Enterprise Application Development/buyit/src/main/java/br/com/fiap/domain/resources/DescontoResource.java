package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Desconto;
import br.com.fiap.domain.service.DescontoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/desconto")
public class DescontoResource {

    private final DescontoService service = DescontoService.build();
    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Desconto> descontos = service.findAll();
        return Response.ok(descontos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Desconto desconto = service.findById(id);
        if (Objects.isNull(desconto)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(desconto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Desconto desconto) {
        if (desconto == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Desconto persistedDesconto = service.persist(desconto);
        URI location = URI.create("/desconto/" + persistedDesconto.getId_desconto());
        return Response.created(location).entity(persistedDesconto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Desconto desconto) {
        Desconto existingDesconto = service.findById(id);
        if (existingDesconto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        desconto.setId_desconto(existingDesconto.getId_desconto());
        Desconto updatedDesconto = service.update(desconto);
        return Response.ok(updatedDesconto).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Desconto desconto = service.findById(id);
        if (desconto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(desconto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}