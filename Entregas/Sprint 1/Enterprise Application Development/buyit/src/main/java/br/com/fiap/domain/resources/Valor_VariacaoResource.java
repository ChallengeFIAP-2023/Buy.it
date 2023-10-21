package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Tipo_Variacao;
import br.com.fiap.domain.entity.Valor_Variacao;
import br.com.fiap.domain.service.Tipo_VariacaoService;
import br.com.fiap.domain.service.Valor_VariacaoService;
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
public class Valor_VariacaoResource implements Resource<Valor_Variacao, Long> {

    private final Valor_VariacaoService service = Valor_VariacaoService.build();

    private final Tipo_VariacaoService serviceTipo_Variacao = Tipo_VariacaoService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateValor_Variacao(Valor_Variacao valor_variacao) {
        // VALOR_VARIACAO
        if (valor_variacao == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Valor_Variacao não pode ser NULL");
        }

        // ID_TIPO_VARIACAO
        if (valor_variacao.getId_tipo_variacao() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Tipo_Variacao do Valor_Variacao não pode ser NULL");
        }
        Tipo_Variacao existingTipo_Variacao = serviceTipo_Variacao.findById(valor_variacao.getId_tipo_variacao().getId_tipo_variacao());
        if (existingTipo_Variacao == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Tipo_Variacao de ID: " + valor_variacao.getId_tipo_variacao().getId_tipo_variacao() + " não encontrado");
        }

        return null;
    }

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Valor_Variacao> valor_variacoes = service.findAll();
        return Response.ok(valor_variacoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Valor_Variacao valor_variacao = service.findById(id);
        if (Objects.isNull(valor_variacao)) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Valor_Variacao de ID: " + id + " não encontrado");
        }
        return Response.ok(valor_variacao).build();
    }

    @GET
    @Path("/tipo_variacao/{id_tipo_variacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdTipo_Variacao(@PathParam("id_tipo_variacao") Long id_tipo_variacao) {
        Tipo_Variacao existingTipo_Variacao = serviceTipo_Variacao.findById(id_tipo_variacao);
        if (existingTipo_Variacao == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Tipo_Variacao de ID: " + id_tipo_variacao + " não encontrado");
        }
        List<Valor_Variacao> valor_variacoes = service.findByIdTipo_Variacao(id_tipo_variacao);
        return Response.ok(valor_variacoes).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(Valor_Variacao valor_variacao) {
        Response validationResponse = validateValor_Variacao(valor_variacao);
        if (validationResponse != null) {
            return validationResponse;
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
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Valor_Variacao de ID: " + id + " não encontrado");
        }
        Response validationResponse = validateValor_Variacao(valor_variacao);
        if (validationResponse != null) {
            return validationResponse;
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
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Valor_Variacao de ID: " + id + " não encontrado");
        }
        service.delete(valor_variacao);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
