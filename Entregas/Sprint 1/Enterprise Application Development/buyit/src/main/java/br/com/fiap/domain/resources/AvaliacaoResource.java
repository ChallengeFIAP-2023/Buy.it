package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Avaliacao;
import br.com.fiap.domain.service.AvaliacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/avaliacao")
public class AvaliacaoResource {

    @Context
    UriInfo uriInfo;

    private final AvaliacaoService service = AvaliacaoService.build();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Avaliacao> avaliacoes = service.findAll();
        return Response.ok(avaliacoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Avaliacao avaliacao = service.findById(id);
        if (Objects.isNull(avaliacao)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(avaliacao).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Avaliacao avaliacao) {
        if (avaliacao == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Avaliacao persistedAvaliacao = service.persist(avaliacao);
        URI location = URI.create("/avaliacao/" + persistedAvaliacao.getId_avaliacao());
        return Response.created(location).entity(persistedAvaliacao).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Avaliacao avaliacao) {
        Avaliacao existingAvaliacao = service.findById(id);
        if (existingAvaliacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        avaliacao.setId_avaliacao(existingAvaliacao.getId_avaliacao());
        Avaliacao updatedAvaliacao = service.update(avaliacao);
        return Response.ok(updatedAvaliacao).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Avaliacao avaliacao = service.findById(id);
        if (avaliacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(avaliacao);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
