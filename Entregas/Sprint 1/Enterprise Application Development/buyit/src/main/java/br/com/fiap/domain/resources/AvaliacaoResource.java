package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Avaliacao;
import br.com.fiap.domain.entity.Pedido;
import br.com.fiap.domain.entity.Usuario;
import br.com.fiap.domain.service.AvaliacaoService;
import br.com.fiap.domain.service.PedidoService;
import br.com.fiap.domain.service.UsuarioService;
import br.com.fiap.infra.CustomErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/avaliacao")
public class AvaliacaoResource implements Resource<Avaliacao, Long> {

    private final AvaliacaoService service = AvaliacaoService.build();

    private final UsuarioService serviceUsuario = UsuarioService.build();

    private final PedidoService servicePedido = PedidoService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateAvaliacao(Avaliacao avaliacao) {

        // AVALIACAO
        if (avaliacao == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Avaliacao não pode ser NULL");
        }

        // ID_USUARIO
        if (avaliacao.getUsuario() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Usuario da Avaliacao não pode ser NULL");
        }
        Usuario existingUsuario = serviceUsuario.findById(avaliacao.getUsuario().getId());
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Usuario de ID: " + avaliacao.getUsuario().getId() + " não encontrado");
        }

        // ID_PEDIDO
        if (avaliacao.getPedido() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Pedido da Avaliacao não pode ser NULL");
        }
        Pedido existingPedido = servicePedido.findById(avaliacao.getPedido().getId());
        if (existingPedido == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Pedido de ID: " + avaliacao.getPedido().getId() + " não encontrado");
        }

        return null;
    }

    @Context
    UriInfo uriInfo;

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
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Avaliacao de ID: " + id + " não encontrada");
        }
        return Response.ok(avaliacao).build();
    }

    @GET
    @Path("/pedido/{id_pedido}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdPedido(@PathParam("id_pedido") Long id_pedido) {
        Avaliacao avaliacao = service.findByIdPedido(id_pedido);
        if (Objects.isNull(avaliacao)) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Pedido de ID: " + id_pedido + " não encontrado");
        }
        return Response.ok(avaliacao).build();
    }

    @GET
    @Path("/usuario/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdUsuario(@PathParam("id_usuario") Long id_usuario) {
        Usuario existingUsuario = serviceUsuario.findById(id_usuario);
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Usuario de ID: " + id_usuario + " não encontrado");
        }
        List<Avaliacao> avaliacoes = service.findByIdUsuario(id_usuario);
        return Response.ok(avaliacoes).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(Avaliacao avaliacao) {
        Response validationResponse = validateAvaliacao(avaliacao);
        if (validationResponse != null) {
            return validationResponse;
        }
        Avaliacao persistedAvaliacao = service.persist(avaliacao);
        URI location = URI.create("/avaliacao/" + persistedAvaliacao.getId());
        return Response.created(location).entity(persistedAvaliacao).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Avaliacao avaliacao) {
        Avaliacao existingAvaliacao = service.findById(id);
        if (existingAvaliacao == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Avaliacao de ID: " + id + " não encontrada");
        }
        Response validationResponse = validateAvaliacao(avaliacao);
        if (validationResponse != null) {
            return validationResponse;
        }
        avaliacao.setId(existingAvaliacao.getId());
        Avaliacao updatedAvaliacao = service.update(avaliacao);
        return Response.ok(updatedAvaliacao).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Avaliacao avaliacao = service.findById(id);
        if (avaliacao == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Avaliacao de ID: " + id + " não encontrada");
        }
        service.delete(avaliacao);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
