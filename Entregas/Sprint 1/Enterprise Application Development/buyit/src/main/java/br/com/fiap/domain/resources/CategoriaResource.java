package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Categoria;
import br.com.fiap.domain.service.CategoriaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/categoria")
public class CategoriaResource {

    @Context
    UriInfo uriInfo;

    private final CategoriaService service = CategoriaService.build();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Categoria> avaliacoes = service.findAll();
        return Response.ok(avaliacoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Categoria categoria = service.findById(id);
        if (Objects.isNull(categoria)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(categoria).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Categoria categoria) {
        if (categoria == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Categoria persistedCategoria = service.persist(categoria);
        URI location = URI.create("/categoria/" + persistedCategoria.getId_categoria());
        return Response.created(location).entity(persistedCategoria).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Categoria categoria) {
        Categoria existingCategoria = service.findById(id);
        if (existingCategoria == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        categoria.setId_categoria(existingCategoria.getId_categoria());
        Categoria updatedCategoria = service.update(categoria);
        return Response.ok(updatedCategoria).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Categoria categoria = service.findById(id);
        if (categoria == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(categoria);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}