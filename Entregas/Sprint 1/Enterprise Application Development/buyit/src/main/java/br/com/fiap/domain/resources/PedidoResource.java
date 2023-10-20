package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Pedido;
import br.com.fiap.domain.service.PedidoService;
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
    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Pedido> avaliacoes = service.findAll();
        return Response.ok(avaliacoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Pedido pedido = service.findById(id);
        if (Objects.isNull(pedido)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(pedido).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Pedido pedido) {
        if (pedido == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
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
            return Response.status(Response.Status.NOT_FOUND).build();
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
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(pedido);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
