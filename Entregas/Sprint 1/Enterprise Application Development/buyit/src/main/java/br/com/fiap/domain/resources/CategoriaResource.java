package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Categoria;
import br.com.fiap.domain.service.CategoriaService;
import br.com.fiap.infra.CustomErrorResponse;
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

    private final CategoriaService service = CategoriaService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateCategoria(Categoria categoria) {
        if (categoria == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Avaliacao não pode ser NULL");
        }
        if (categoria.getNm_categoria() == null || categoria.getNm_categoria().isEmpty() || categoria.getNm_categoria().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Nome da Categoria não pode ser NULL ou vazio");
        }

        return null;
    }

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Categoria> categorias = service.findAll();
        return Response.ok(categorias).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Categoria categoria = service.findById(id);
        if (Objects.isNull(categoria)) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Categoria de ID: " + id + " não encontrada");
        }
        return Response.ok(categoria).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Categoria categoria) {
        if (categoria == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Categoria não pode ser NULL");
        }
        Response validationResponse = validateCategoria(categoria);
        if (validationResponse != null) {
            return validationResponse;
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
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Categoria de ID: " + id + " não encontrada");
        }
        Response validationResponse = validateCategoria(categoria);
        if (validationResponse != null) {
            return validationResponse;
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
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Categoria de ID: " + id + " não encontrada");
        }
        service.delete(categoria);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}