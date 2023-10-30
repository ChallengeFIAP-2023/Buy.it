package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.*;
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

@Path("/pedido")
public class PedidoResource implements Resource<Pedido, Long> {

    private final PedidoService service = PedidoService.build();

    private final UsuarioService serviceUsuario = UsuarioService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validatePedido(Pedido pedido) {
        // PEDIDO
        if (pedido == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Pedido não pode ser NULL");
        }

        // USUARIO
        if (pedido.getUsuario() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Usuario do Pedido não pode ser NULL");
        }
        Usuario existingUsuario = serviceUsuario.findById(pedido.getUsuario().getId());
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Usuario de ID: " + pedido.getUsuario().getId() + " não encontrado");
        }

        // STATUS
        if (pedido.getStatus() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Status do Pedido não pode ser NULL ou vazio");
        }

        // DATA
        if (pedido.getData() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Data do Pedido não pode ser NULL ou vazio");
        }

        // VALOR
        if (pedido.getValor() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Valor do Pedido não pode ser NULL ou vazio");
        }

        return null;
    }

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Pedido> pedidos = service.findAll();
        return Response.ok(pedidos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Pedido pedido = service.findById(id);
        if (Objects.isNull(pedido)) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Pedido de ID: " + id + " não encontrado");
        }
        return Response.ok(pedido).build();
    }

    @GET
    @Path("/usuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdUsuario(@PathParam("id") Long id) {
        Usuario existingUsuario = serviceUsuario.findById(id);
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Usuario de ID: " + id + " não encontrado");
        }
        List<Pedido> pedidos = service.findByIdUsuario(id);
        return Response.ok(pedidos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(Pedido pedido) {
        Response validationResponse = validatePedido(pedido);
        if (validationResponse != null) {
            return validationResponse;
        }
        Pedido persistedPedido = service.persist(pedido);
        URI location = URI.create("/pedido/" + persistedPedido.getId());
        return Response.created(location).entity(persistedPedido).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Pedido pedido) {
        Pedido existingPedido = service.findById(id);
        if (existingPedido == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Pedido de ID: " + id + " não encontrado");
        }
        Response validationResponse = validatePedido(pedido);
        if (validationResponse != null) {
            return validationResponse;
        }
        pedido.setId(existingPedido.getId());
        Pedido updatedPedido = service.update(pedido);
        return Response.ok(updatedPedido).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Pedido pedido = service.findById(id);
        if (pedido == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Pedido de ID: " + id + " não encontrado");
        }
        service.delete(pedido);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
