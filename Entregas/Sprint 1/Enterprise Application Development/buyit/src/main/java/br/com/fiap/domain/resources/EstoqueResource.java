package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Estoque;
import br.com.fiap.domain.service.EstoqueService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/estoque")
public class EstoqueResource {

    private final EstoqueService service = EstoqueService.build();
    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Estoque> estoques = service.findAll();
        return Response.ok(estoques).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Estoque estoque = service.findById(id);
        if (Objects.isNull(estoque)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(estoque).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Estoque estoque) {
        if (estoque == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Estoque persistedEstoque = service.persist(estoque);
        URI location = URI.create("/estoque/" + persistedEstoque.getId_estoque());
        return Response.created(location).entity(persistedEstoque).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Estoque estoque) {
        Estoque existingEstoque = service.findById(id);
        if (existingEstoque == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        estoque.setId_estoque(existingEstoque.getId_estoque());
        Estoque updatedEstoque = service.update(estoque);
        return Response.ok(updatedEstoque).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Estoque estoque = service.findById(id);
        if (estoque == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(estoque);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
