package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.TipoVariacao;
import br.com.fiap.domain.entity.ValorVariacao;
import br.com.fiap.domain.service.TipoVariacaoService;
import br.com.fiap.domain.service.ValorVariacaoService;
import br.com.fiap.infra.CustomErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/valor_variacao")
public class ValorVariacaoResource implements Resource<ValorVariacao, Long> {

    private final ValorVariacaoService service = ValorVariacaoService.build();

    private final TipoVariacaoService serviceTipoVariacao = TipoVariacaoService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateValorVariacao(ValorVariacao valor_variacao) {
        // VALOR_VARIACAO
        if (valor_variacao == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ValorVariacao não pode ser NULL");
        }

        // TIPO_VARIACAO
        if (valor_variacao.getTipoVariacao() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do TipoVariacao do ValorVariacao não pode ser NULL");
        }
        TipoVariacao existingTipoVariacao = serviceTipoVariacao.findById(valor_variacao.getTipoVariacao().getId());
        if (existingTipoVariacao == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "TipoVariacao de ID: " + valor_variacao.getTipoVariacao().getId() + " não encontrado");
        }

        return null;
    }

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<ValorVariacao> valor_variacoes = service.findAll();
        return Response.ok(valor_variacoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        ValorVariacao valor_variacao = service.findById(id);
        if (Objects.isNull(valor_variacao)) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "ValorVariacao de ID: " + id + " não encontrado");
        }
        return Response.ok(valor_variacao).build();
    }

    @GET
    @Path("/tipo_variacao/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdTipoVariacao(@PathParam("id") Long id) {
        TipoVariacao existingTipoVariacao = serviceTipoVariacao.findById(id);
        if (existingTipoVariacao == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "TipoVariacao de ID: " + id + " não encontrado");
        }
        List<ValorVariacao> valor_variacoes = service.findByIdTipoVariacao(id);
        return Response.ok(valor_variacoes).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(ValorVariacao valor_variacao) {
        Response validationResponse = validateValorVariacao(valor_variacao);
        if (validationResponse != null) {
            return validationResponse;
        }
        ValorVariacao persistedValorVariacao = service.persist(valor_variacao);
        URI location = URI.create("/valor_variacao/" + persistedValorVariacao.getId());
        return Response.created(location).entity(persistedValorVariacao).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, ValorVariacao valor_variacao) {
        ValorVariacao existingValorVariacao = service.findById(id);
        if (existingValorVariacao == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "ValorVariacao de ID: " + id + " não encontrado");
        }
        Response validationResponse = validateValorVariacao(valor_variacao);
        if (validationResponse != null) {
            return validationResponse;
        }
        valor_variacao.setId(existingValorVariacao.getId());
        ValorVariacao updatedValorVariacao = service.update(valor_variacao);
        return Response.ok(updatedValorVariacao).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        ValorVariacao valor_variacao = service.findById(id);
        if (valor_variacao == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "ValorVariacao de ID: " + id + " não encontrado");
        }
        service.delete(valor_variacao);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
