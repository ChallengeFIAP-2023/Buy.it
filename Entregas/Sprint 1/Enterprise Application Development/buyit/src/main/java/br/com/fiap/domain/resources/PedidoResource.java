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
public class PedidoResource {

    private final PedidoService service = PedidoService.build();

    private final UsuarioService serviceUsuario = UsuarioService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validatePedido(Pedido pedido) {
        // PEDIDO
        if (pedido == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Pedido não pode ser NULL");
        }

        // ID_USUARIO
        if (pedido.getId_usuario() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Usuario do Pedido não pode ser NULL");
        }
        Usuario existingUsuario = serviceUsuario.findById(pedido.getId_usuario().getId_usuario());
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Usuario de ID: " + pedido.getId_usuario().getId_usuario() + " não encontrado");
        }

        // STATUS_PEDIDO
        if (pedido.getStatus_pedido() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Status do Pedido não pode ser NULL ou vazio");
        }

        // DATA_PEDIDO
        if (pedido.getData_pedido() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Data do Pedido não pode ser NULL ou vazio");
        }

        // VALOR_PEDIDO
        if (pedido.getValor_pedido() == null) {
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
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Pedido de ID: " + id + " não encontrado");
        }
        return Response.ok(pedido).build();
    }

    @GET
    @Path("/usuario/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdUsuario(@PathParam("id_usuario") Long id_usuario) {
        Usuario existingUsuario = serviceUsuario.findById(id_usuario);
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Usuario de ID: " + id_usuario + " não encontrado");
        }
        List<Pedido> pedidos = service.findByIdUsuario(id_usuario);
        return Response.ok(pedidos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Pedido pedido) {
        Response validationResponse = validatePedido(pedido);
        if (validationResponse != null) {
            return validationResponse;
        }
        Pedido persistedPedido = service.persist(pedido);
        URI location = URI.create("/pedido/" + persistedPedido.getId_pedido());
        return Response.created(location).entity(persistedPedido).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Pedido pedido) {
        Pedido existingPedido = service.findById(id);
        if (existingPedido == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Pedido de ID: " + id + " não encontrado");
        }
        Response validationResponse = validatePedido(pedido);
        if (validationResponse != null) {
            return validationResponse;
        }
        pedido.setId_pedido(existingPedido.getId_pedido());
        Pedido updatedPedido = service.update(pedido);
        return Response.ok(updatedPedido).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Pedido pedido = service.findById(id);
        if (pedido == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Pedido de ID: " + id + " não encontrado");
        }
        service.delete(pedido);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
