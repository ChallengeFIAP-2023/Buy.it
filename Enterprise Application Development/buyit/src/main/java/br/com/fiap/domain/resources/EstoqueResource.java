package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.*;
import br.com.fiap.domain.service.*;
import br.com.fiap.infra.CustomErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/estoque")
public class EstoqueResource implements Resource<Estoque, Long> {

    private final EstoqueService service = EstoqueService.build();

    private final ProdutoService serviceProduto = ProdutoService.build();

    private final ValorVariacaoService serviceValorVariacao = ValorVariacaoService.build();

    private final UsuarioService serviceUsuario = UsuarioService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateEstoque(Estoque estoque) {
        // ESTOQUE
        if (estoque == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Estoque não pode ser NULL");
        }

        // PRODUTO
        if (estoque.getProduto() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Produto do Estoque não pode ser NULL");
        }
        Produto existingProduto = serviceProduto.findById(estoque.getProduto().getId());
        if (existingProduto == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Produto de ID: " + estoque.getProduto().getId() + " não encontrado");
        }

        // USUARIO
        if (estoque.getUsuario() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Usuario do Estoque não pode ser NULL");
        }
        Usuario existingUsuario = serviceUsuario.findById(estoque.getUsuario().getId());
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Usuario de ID: " + estoque.getUsuario().getId() + " não encontrado");
        }

        // VALOR_VARIACAO
        if (estoque.getValorVariacao() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do ValorVariacao do Estoque não pode ser NULL");
        }
        ValorVariacao existingValorVariacao = serviceValorVariacao.findById(estoque.getValorVariacao().getId());
        if (existingValorVariacao == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "ValorVariacao de ID: " + estoque.getValorVariacao().getId() + " não encontrado");
        }

        // QUANTIDADE
        if (estoque.getQuantidade() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Quantidade do Estoque não pode ser NULL ou vazio");
        }

        // PRECO_UNITARIO
        if (estoque.getPrecoUnitario() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Preço Unitário do Estoque não pode ser NULL ou vazio");
        }
        
        return null;
    }

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Estoque> estoques = service.findAll();
        return Response.ok(estoques).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Estoque estoque = service.findById(id);
        if (Objects.isNull(estoque)) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Estoque de ID: " + id + " não encontrado");
        }
        return Response.ok(estoque).build();
    }

    @GET
    @Path("/produto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdProduto(@PathParam("id") Long id) {
        Produto existingProduto = serviceProduto.findById(id);
        if (existingProduto == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Produto de ID: " + id + " não encontrado");
        }
        List<Estoque> estoques = service.findByIdProduto(id);
        return Response.ok(estoques).build();
    }

    @GET
    @Path("/valor_variacao/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdValorVariacao(@PathParam("id") Long id) {
        ValorVariacao existingValorVariacao = serviceValorVariacao.findById(id);
        if (existingValorVariacao == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "ValorVariacao de ID: " + id + " não encontrado");
        }
        List<Estoque> estoques = service.findByIdValorVariacao(id);
        return Response.ok(estoques).build();
    }

    @GET
    @Path("/usuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdUsuario(@PathParam("id") Long id) {
        Usuario existingUsuario = serviceUsuario.findById(id);
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Usuario de ID: " + id + " não encontrado");
        }
        List<Estoque> estoques = service.findByIdUsuario(id);
        return Response.ok(estoques).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(Estoque estoque) {
        Response validationResponse = validateEstoque(estoque);
        if (validationResponse != null) {
            return validationResponse;
        }
        Estoque persistedEstoque = service.persist(estoque);
        URI location = URI.create("/estoque/" + persistedEstoque.getId());
        return Response.created(location).entity(persistedEstoque).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Estoque estoque) {
        Estoque existingEstoque = service.findById(id);
        if (existingEstoque == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Estoque de ID: " + id + " não encontrado");
        }
        Response validationResponse = validateEstoque(estoque);
        if (validationResponse != null) {
            return validationResponse;
        }
        estoque.setId(existingEstoque.getId());
        Estoque updatedEstoque = service.update(estoque);
        return Response.ok(updatedEstoque).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Estoque estoque = service.findById(id);
        if (estoque == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Estoque de ID: " + id + " não encontrado");
        }
        service.delete(estoque);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
