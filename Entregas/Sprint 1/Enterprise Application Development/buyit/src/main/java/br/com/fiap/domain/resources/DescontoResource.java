package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Desconto;
import br.com.fiap.domain.entity.Estoque;
import br.com.fiap.domain.service.DescontoService;
import br.com.fiap.domain.service.EstoqueService;
import br.com.fiap.infra.CustomErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/desconto")
public class DescontoResource implements Resource<Desconto, Long> {

    private final DescontoService service = DescontoService.build();

    private final EstoqueService serviceEstoque = EstoqueService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateDesconto(Desconto desconto) {
        // DESCONTO (ENTIDADE)
        if (desconto == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Desconto não pode ser NULL");
        }

        // QTD_MIN_PRODUTO
        if (desconto.getQtd_min_produto() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Quantidade Mínima do Produto não pode ser NULL");
        }

        // DESCONTO (ATRIBUTO)
        if (desconto.getDesconto() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Desconto não pode ser NULL");
        }

        // ID_ESTOQUE
        if (desconto.getId_estoque() == null || serviceEstoque.findById(desconto.getId_estoque().getId_estoque()) == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Estoque de ID: " + desconto.getId_estoque().getId_estoque() + " não encontrado");
        }
        return null;
    }
    
    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Desconto> descontos = service.findAll();
        return Response.ok(descontos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Desconto desconto = service.findById(id);
        if (Objects.isNull(desconto)) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Desconto de ID: " + id + " não encontrado");
        }
        return Response.ok(desconto).build();
    }

    @GET
    @Path("/estoque/{id_estoque}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdEstoque(@PathParam("id_estoque") Long id_estoque) {
        Estoque existingEstoque = serviceEstoque.findById(id_estoque);
        if (existingEstoque == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Estoque de ID: " + id_estoque + " não encontrado");
        }
        List<Desconto> descontos = service.findByIdEstoque(id_estoque);
        return Response.ok(descontos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(Desconto desconto) {
        Response validationResponse = validateDesconto(desconto);
        if (validationResponse != null) {
            return validationResponse;
        }
        Desconto persistedDesconto = service.persist(desconto);
        URI location = URI.create("/desconto/" + persistedDesconto.getId_desconto());
        return Response.created(location).entity(persistedDesconto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Desconto desconto) {
        Desconto existingDesconto = service.findById(id);
        if (existingDesconto == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Desconto de ID: " + id + " não encontrado");
        }
        Response validationResponse = validateDesconto(desconto);
        if (validationResponse != null) {
            return validationResponse;
        }
        desconto.setId_desconto(existingDesconto.getId_desconto());
        Desconto updatedDesconto = service.update(desconto);
        return Response.ok(updatedDesconto).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Desconto desconto = service.findById(id);
        if (desconto == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Desconto de ID: " + id + " não encontrado");
        }
        service.delete(desconto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}