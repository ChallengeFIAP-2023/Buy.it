package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Tipo_Variacao;
import br.com.fiap.domain.service.Tipo_VariacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/tipo_variacao")
public class Tipo_VariacaoResource {

    private final Tipo_VariacaoService service = Tipo_VariacaoService.build();
    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Tipo_Variacao> tipo_variacoes = service.findAll();
        return Response.ok(tipo_variacoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Tipo_Variacao tipo_variacao = service.findById(id);
        if (Objects.isNull(tipo_variacao)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(tipo_variacao).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Tipo_Variacao tipo_variacao) {
        if (tipo_variacao == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Tipo_Variacao persistedTipo_Variacao = service.persist(tipo_variacao);
        URI location = URI.create("/tipo_variacao/" + persistedTipo_Variacao.getId_tipo_variacao());
        return Response.created(location).entity(persistedTipo_Variacao).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Tipo_Variacao tipo_variacao) {
        Tipo_Variacao existingTipo_Variacao = service.findById(id);
        if (existingTipo_Variacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        tipo_variacao.setId_tipo_variacao(existingTipo_Variacao.getId_tipo_variacao());
        Tipo_Variacao updatedTipo_Variacao = service.update(tipo_variacao);
        return Response.ok(updatedTipo_Variacao).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Tipo_Variacao tipo_variacao = service.findById(id);
        if (tipo_variacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(tipo_variacao);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
