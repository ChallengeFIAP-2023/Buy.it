package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Tag;
import br.com.fiap.domain.service.TagService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/tag")
public class TagResource {

    private final TagService service = TagService.build();
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
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(tag).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Tag tag) {
        if (tag == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Tag persistedTag = service.persist(tag);
        URI location = URI.create("/tag/" + persistedTag.getId_tag());
        return Response.created(location).entity(persistedTag).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Tag tag) {
        Tag existingTag = service.findById(id);
        if (existingTag == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        tag.setId_tag(existingTag.getId_tag());
        Tag updatedTag = service.update(tag);
        return Response.ok(updatedTag).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Tag tag = service.findById(id);
        if (tag == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(tag);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
