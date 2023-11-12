package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.*;
import br.com.fiap.domain.service.TagService;
import br.com.fiap.infra.CustomErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/tag")
public class TagResource implements Resource<Tag, Long> {

    private final TagService service = TagService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateTag(Tag tag) {
        // TAG
        if (tag == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Tag não pode ser NULL");
        }

        // NOME
        if (tag.getNome() == null || tag.getNome().isEmpty() || tag.getNome().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Nome da Tag não pode ser NULL ou vazio");
        }

        return null;
    }

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Tag> tags = service.findAll();
        return Response.ok(tags).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Tag tag = service.findById(id);
        if (Objects.isNull(tag)) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Tag de ID: " + id + " não encontrada");
        }
        return Response.ok(tag).build();
    }

    @GET
    @Path("/name/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("nome") String nome) {
        List<Tag> tags = service.findByName(nome);
        return Response.ok(tags).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(Tag tag) {
        Response validationResponse = validateTag(tag);
        if (validationResponse != null) {
            return validationResponse;
        }
        Tag persistedTag = service.persist(tag);
        URI location = URI.create("/tag/" + persistedTag.getId());
        return Response.created(location).entity(persistedTag).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Tag tag) {
        Tag existingTag = service.findById(id);
        if (existingTag == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Tag de ID: " + id + " não encontrada");
        }
        Response validationResponse = validateTag(tag);
        if (validationResponse != null) {
            return validationResponse;
        }
        tag.setId(existingTag.getId());
        Tag updatedTag = service.update(tag);
        return Response.ok(updatedTag).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Tag tag = service.findById(id);
        if (tag == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Tag de ID: " + id + " não encontrada");
        }
        service.delete(tag);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
