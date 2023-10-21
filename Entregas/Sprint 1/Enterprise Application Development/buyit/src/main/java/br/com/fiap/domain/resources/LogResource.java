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
public class LogResource {

    private final LogService service = LogService.build();

    private final PedidoService servicePedido = PedidoService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateLog(Log log) {

        // LOG
        if (log == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Log não pode ser NULL");
        }

        // ID_PEDIDO
        if (log.getId_pedido() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Pedido do Log não pode ser NULL");
        }
        Pedido existingPedido = servicePedido.findById(log.getId_pedido().getId_pedido());
        if (existingPedido == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Pedido de ID: " + log.getId_pedido().getId_pedido() + " não encontrado");
        }

        // TIMESTAMP_LOG
        if (log.getTimestamp_log() == null || log.getTimestamp_log().isEmpty() || log.getTimestamp_log().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O TimeStamp do Log não pode ser NULL ou vazio");
        }

        // NM_LOG
        if (log.getNm_log() == null || log.getNm_log().isEmpty() || log.getNm_log().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Nome do Log não pode ser NULL ou vazio");
        }

        // DS_LOG
        if (log.getDs_log() == null || log.getDs_log().isEmpty() || log.getDs_log().isBlank()) {
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
    @Path("/name/{nm_log}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("nm_log") String nm_log) {
        List<Log> logs = service.findByName(nm_log);
        return Response.ok(logs).build();
    }

    @GET
    @Path("/pedido/{id_pedido}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdPedido(@PathParam("id_pedido") Long id_pedido) {
        Pedido existingPedido = servicePedido.findById(id_pedido);
        if (existingPedido == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Pedido de ID: " + id_pedido + " não encontrado");
        }
        List<Log> logs = service.findByIdPedido(id_pedido);
        return Response.ok(logs).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Log log) {
        Response validationResponse = validateLog(log);
        if (validationResponse != null) {
            return validationResponse;
        }
        Log persistedLog = service.persist(log);
        URI location = URI.create("/log/" + persistedLog.getId_log());
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
        log.setId_log(existingLog.getId_log());
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
