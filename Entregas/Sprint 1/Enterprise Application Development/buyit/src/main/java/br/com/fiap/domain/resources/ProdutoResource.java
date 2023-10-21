package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.*;
import br.com.fiap.domain.service.CategoriaService;
import br.com.fiap.domain.service.ProdutoService;
import br.com.fiap.infra.CustomErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/produto")
public class ProdutoResource {

    private final ProdutoService service = ProdutoService.build();

    private final CategoriaService serviceCategoria = CategoriaService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateProduto(Produto produto) {

        // PRODUTO
        if (produto == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Produto não pode ser NULL");
        }

        // NM_PRODUTO
        if (produto.getNm_produto() == null || produto.getNm_produto().isEmpty() || produto.getNm_produto().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Nome do Produto não pode ser NULL ou vazio");
        }

        // MARCA_PRODUTO
        if (produto.getMarca_produto() == null || produto.getMarca_produto().isEmpty() || produto.getMarca_produto().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Nome do Produto não pode ser NULL ou vazio");
        }

        // MENOR_PRECO_PRODUTO
        if (produto.getMenor_preco_produto() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Menor Preço do Produto não pode ser NULL ou vazio");
        }

        return null;
    }

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Produto> produtos = service.findAll();
        return Response.ok(produtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Produto produto = service.findById(id);
        if (Objects.isNull(produto)) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Produto de ID: " + id + " não encontrado");
        }
        return Response.ok(produto).build();
    }

    @GET
    @Path("/name/{nm_produto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("nm_produto") String nm_produto) {
        List<Produto> produtos = service.findByName(nm_produto);
        return Response.ok(produtos).build();
    }

    @GET
    @Path("/categoria/{id_categoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdCategoria(@PathParam("id_categoria") Long id_categoria) {
        Categoria existingCategoria = serviceCategoria.findById(id_categoria);
        if (existingCategoria == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Categoria de ID: " + id_categoria + " não encontrada");
        }
        List<Produto> produtos = service.findByIdCategoria(id_categoria);
        return Response.ok(produtos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Produto produto) {
        Response validationResponse = validateProduto(produto);
        if (validationResponse != null) {
            return validationResponse;
        }
        Produto persistedProduto = service.persist(produto);
        URI location = URI.create("/produto/" + persistedProduto.getId_produto());
        return Response.created(location).entity(persistedProduto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Produto produto) {
        Produto existingProduto = service.findById(id);
        if (existingProduto == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Produto de ID: " + id + " não encontrado");
        }
        Response validationResponse = validateProduto(produto);
        if (validationResponse != null) {
            return validationResponse;
        }
        produto.setId_produto(existingProduto.getId_produto());
        Produto updatedProduto = service.update(produto);
        return Response.ok(updatedProduto).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Produto produto = service.findById(id);
        if (produto == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "Produto de ID: " + id + " não encontrado");
        }
        service.delete(produto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
