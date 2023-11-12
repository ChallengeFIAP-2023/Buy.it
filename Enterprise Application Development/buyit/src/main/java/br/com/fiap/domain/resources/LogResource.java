package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.*;
import br.com.fiap.domain.service.LogService;
import br.com.fiap.domain.service.PedidoService;
import br.com.fiap.infra.CustomErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/log")
public class LogResource implements Resource<Log, Long> {

    private final LogService service = LogService.build();

    private final PedidoService servicePedido = PedidoService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateLog(Log log) {

        // LOG
        if (log == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Log não pode ser NULL");
        }

        // PEDIDO
        if (log.getPedido() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Pedido do Log não pode ser NULL");
        }
        Pedido existingPedido = servicePedido.findById(log.getPedido().getId());
        if (existingPedido == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Pedido de ID: " + log.getPedido().getId() + " não encontrado");
        }

        // TIMESTAMP
        if (log.getTimestamp() == null || log.getTimestamp().isEmpty() || log.getTimestamp().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O TimeStamp do Log não pode ser NULL ou vazio");
        }

        // NOME
        if (log.getNome() == null || log.getNome().isEmpty() || log.getNome().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Nome do Log não pode ser NULL ou vazio");
        }

        // DESCRICAO
        if (log.getDescricao() == null || log.getDescricao().isEmpty() || log.getDescricao().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Descricao do Log não pode ser NULL ou vazio");
        }

        return null;
    }

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Log> logs = service.findAll();
        return Response.ok(logs).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Log log = service.findById(id);
        if (Objects.isNull(log)) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Log de ID: " + id + " não encontrado");
        }
        return Response.ok(log).build();
    }

    @GET
    @Path("/name/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("nome") String nome) {
        List<Log> logs = service.findByName(nome);
        return Response.ok(logs).build();
    }

    @GET
    @Path("/pedido/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdPedido(@PathParam("id") Long id) {
        Pedido existingPedido = servicePedido.findById(id);
        if (existingPedido == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Pedido de ID: " + id + " não encontrado");
        }
        List<Log> logs = service.findByIdPedido(id);
        return Response.ok(logs).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(Log log) {
        Response validationResponse = validateLog(log);
        if (validationResponse != null) {
            return validationResponse;
        }
        Log persistedLog = service.persist(log);
        URI location = URI.create("/log/" + persistedLog.getId());
        return Response.created(location).entity(persistedLog).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Log log) {
        Log existingLog = service.findById(id);
        if (existingLog == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Log de ID: " + id + " não encontrado");
        }
        Response validationResponse = validateLog(log);
        if (validationResponse != null) {
            return validationResponse;
        }
        log.setId(existingLog.getId());
        Log updatedLog = service.update(log);
        return Response.ok(updatedLog).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Log log = service.findById(id);
        if (log == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Log de ID: " + id + " não encontrado");
        }
        service.delete(log);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
