package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.TipoVariacao;
import br.com.fiap.domain.service.TipoVariacaoService;
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
public class TipoVariacaoResource implements Resource<TipoVariacao, Long> {

    private final TipoVariacaoService service = TipoVariacaoService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateTipoVariacao(TipoVariacao tipo_variacao) {
        // TIPO_VARIACAO
        if (tipo_variacao == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O TipoVariacao não pode ser NULL");
        }

        // NM_TIPO_VARIACAO
        if (tipo_variacao.getNome() == null || tipo_variacao.getNome().isEmpty() || tipo_variacao.getNome().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Nome do TipoVariacao não pode ser NULL ou vazio");
        }

        return null;
    }

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<TipoVariacao> tipo_variacoes = service.findAll();
        return Response.ok(tipo_variacoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        TipoVariacao tipo_variacao = service.findById(id);
        if (Objects.isNull(tipo_variacao)) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "TipoVariacao de ID: " + id + " não encontrado");
        }
        return Response.ok(tipo_variacao).build();
    }

    @GET
    @Path("/name/{nm_tipo_variacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("nm_tipo_variacao") String nm_tipo_variacao) {
        List<TipoVariacao> tipo_variacoes = service.findByName(nm_tipo_variacao);
        return Response.ok(tipo_variacoes).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(TipoVariacao tipo_variacao) {
        Response validationResponse = validateTipoVariacao(tipo_variacao);
        if (validationResponse != null) {
            return validationResponse;
        }
        TipoVariacao persistedTipoVariacao = service.persist(tipo_variacao);
        URI location = URI.create("/tipo_variacao/" + persistedTipoVariacao.getId());
        return Response.created(location).entity(persistedTipoVariacao).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, TipoVariacao tipo_variacao) {
        TipoVariacao existingTipoVariacao = service.findById(id);
        if (existingTipoVariacao == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "TipoVariacao de ID: " + id + " não encontrado");
        }
        Response validationResponse = validateTipoVariacao(tipo_variacao);
        if (validationResponse != null) {
            return validationResponse;
        }
        tipo_variacao.setId(existingTipoVariacao.getId());
        TipoVariacao updatedTipoVariacao = service.update(tipo_variacao);
        return Response.ok(updatedTipoVariacao).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        TipoVariacao tipo_variacao = service.findById(id);
        if (tipo_variacao == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "TipoVariacao de ID: " + id + " não encontrado");
        }
        service.delete(tipo_variacao);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
