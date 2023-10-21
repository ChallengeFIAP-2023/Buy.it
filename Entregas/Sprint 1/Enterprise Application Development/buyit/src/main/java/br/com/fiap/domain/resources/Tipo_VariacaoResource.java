package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Avaliacao;
import br.com.fiap.domain.entity.Tipo_Variacao;
import br.com.fiap.domain.service.Tipo_VariacaoService;
import br.com.fiap.infra.CustomErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/tipo_variacao")
public class Tipo_VariacaoResource implements Resource<Tipo_Variacao, Long> {

    private final Tipo_VariacaoService service = Tipo_VariacaoService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateTipo_Variacao(Tipo_Variacao tipo_variacao) {
        // TIPO_VARIACAO
        if (tipo_variacao == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Tipo_Variacao não pode ser NULL");
        }

        // NM_TIPO_VARIACAO
        if (tipo_variacao.getNm_tipo_variacao() == null || tipo_variacao.getNm_tipo_variacao().isEmpty() || tipo_variacao.getNm_tipo_variacao().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Nome do Tipo_Variacao não pode ser NULL ou vazio");
        }

        return null;
    }

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
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Tipo_Variacao de ID: " + id + " não encontrado");
        }
        return Response.ok(tipo_variacao).build();
    }

    @GET
    @Path("/name/{nm_tipo_variacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("nm_tipo_variacao") String nm_tipo_variacao) {
        List<Tipo_Variacao> tipo_variacoes = service.findByName(nm_tipo_variacao);
        return Response.ok(tipo_variacoes).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(Tipo_Variacao tipo_variacao) {
        Response validationResponse = validateTipo_Variacao(tipo_variacao);
        if (validationResponse != null) {
            return validationResponse;
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
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Tipo_Variacao de ID: " + id + " não encontrado");
        }
        Response validationResponse = validateTipo_Variacao(tipo_variacao);
        if (validationResponse != null) {
            return validationResponse;
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
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Tipo_Variacao de ID: " + id + " não encontrado");
        }
        service.delete(tipo_variacao);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
