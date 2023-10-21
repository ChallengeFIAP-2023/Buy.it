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
public class EstoqueResource {

    private final EstoqueService service = EstoqueService.build();

    private final ProdutoService serviceProduto = ProdutoService.build();

    private final Valor_VariacaoService serviceValor_Variacao = Valor_VariacaoService.build();

    private final UsuarioService serviceUsuario = UsuarioService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateEstoque(Estoque estoque) {
        // ESTOQUE
        if (estoque == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Estoque não pode ser NULL");
        }

        // ID_PRODUTO
        if (estoque.getId_produto() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Produto do Estoque não pode ser NULL");
        }
        Produto existingProduto = serviceProduto.findById(estoque.getId_produto().getId_produto());
        if (existingProduto == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Produto de ID: " + estoque.getId_produto().getId_produto() + " não encontrado");
        }

        // ID_USUARIO
        if (estoque.getId_usuario() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Usuario do Estoque não pode ser NULL");
        }
        Usuario existingUsuario = serviceUsuario.findById(estoque.getId_usuario().getId_usuario());
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Usuario de ID: " + estoque.getId_usuario().getId_usuario() + " não encontrado");
        }

        // ID_VALOR_VARIACAO
        if (estoque.getId_valor_variacao() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O ID do Valor_Variacao do Estoque não pode ser NULL");
        }
        Valor_Variacao existingValor_Variacao = serviceValor_Variacao.findById(estoque.getId_valor_variacao().getId_valor_variacao());
        if (existingValor_Variacao == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Valor_Variacao de ID: " + estoque.getId_valor_variacao().getId_valor_variacao() + " não encontrado");
        }

        // QTD_ESTOQUE
        if (estoque.getQtd_estoque() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Quantidade do Estoque não pode ser NULL ou vazio");
        }

        // PRECO_UNITARIO
        if (estoque.getPreco_unitario() == null) {
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
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Estoque de ID: " + id + " não encontrado");
        }
        return Response.ok(estoque).build();
    }

    @GET
    @Path("/produto/{id_produto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdProduto(@PathParam("id_produto") Long id_produto) {
        Produto existingProduto = serviceProduto.findById(id_produto);
        if (existingProduto == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Produto de ID: " + id_produto + " não encontrado");
        }
        List<Estoque> estoques = service.findByIdProduto(id_produto);
        return Response.ok(estoques).build();
    }

    @GET
    @Path("/valor_variacao/{id_valor_variacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdValor_Variacao(@PathParam("id_valor_variacao") Long id_valor_variacao) {
        Valor_Variacao existingValor_Variacao = serviceValor_Variacao.findById(id_valor_variacao);
        if (existingValor_Variacao == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Valor_Variacao de ID: " + id_valor_variacao + " não encontrado");
        }
        List<Estoque> estoques = service.findByIdValor_Variacao(id_valor_variacao);
        return Response.ok(estoques).build();
    }

    @GET
    @Path("/usuario/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdUsuario(@PathParam("id_usuario") Long id_usuario) {
        Usuario existingUsuario = serviceUsuario.findById(id_usuario);
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Usuario de ID: " + id_usuario + " não encontrado");
        }
        List<Estoque> estoques = service.findByIdUsuario(id_usuario);
        return Response.ok(estoques).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Estoque estoque) {
        Response validationResponse = validateEstoque(estoque);
        if (validationResponse != null) {
            return validationResponse;
        }
        Estoque persistedEstoque = service.persist(estoque);
        URI location = URI.create("/estoque/" + persistedEstoque.getId_estoque());
        return Response.created(location).entity(persistedEstoque).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Estoque estoque) {
        Estoque existingEstoque = service.findById(id);
        if (existingEstoque == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Estoque de ID: " + id + " não encontrado");
        }
        Response validationResponse = validateEstoque(estoque);
        if (validationResponse != null) {
            return validationResponse;
        }
        estoque.setId_estoque(existingEstoque.getId_estoque());
        Estoque updatedEstoque = service.update(estoque);
        return Response.ok(updatedEstoque).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Estoque estoque = service.findById(id);
        if (estoque == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Estoque de ID: " + id + " não encontrado");
        }
        service.delete(estoque);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
