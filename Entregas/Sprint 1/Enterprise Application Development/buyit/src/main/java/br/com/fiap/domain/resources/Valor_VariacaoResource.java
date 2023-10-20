package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Valor_Variacao;
import br.com.fiap.domain.service.Valor_VariacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/valor_variacao")
public class Valor_VariacaoResource {

    private final Valor_VariacaoService service = Valor_VariacaoService.build();
    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Valor_Variacao> avaliacoes = service.findAll();
        return Response.ok(avaliacoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Valor_Variacao valor_variacao = service.findById(id);
        if (Objects.isNull(valor_variacao)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(valor_variacao).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Valor_Variacao valor_variacao) {
        if (valor_variacao == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Valor_Variacao persistedValor_Variacao = service.persist(valor_variacao);
        URI location = URI.create("/valor_variacao/" + persistedValor_Variacao.getId_valor_variacao());
        return Response.created(location).entity(persistedValor_Variacao).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Valor_Variacao valor_variacao) {
        Valor_Variacao existingValor_Variacao = service.findById(id);
        if (existingValor_Variacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        valor_variacao.setId_valor_variacao(existingValor_Variacao.getId_valor_variacao());
        Valor_Variacao updatedValor_Variacao = service.update(valor_variacao);
        return Response.ok(updatedValor_Variacao).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Valor_Variacao valor_variacao = service.findById(id);
        if (valor_variacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(valor_variacao);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
