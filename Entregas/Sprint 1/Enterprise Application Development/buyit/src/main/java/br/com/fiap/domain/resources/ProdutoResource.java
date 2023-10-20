package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.service.ProdutoService;
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
    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Produto> avaliacoes = service.findAll();
        return Response.ok(avaliacoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Produto produto = service.findById(id);
        if (Objects.isNull(produto)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(produto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Produto produto) {
        if (produto == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
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
            return Response.status(Response.Status.NOT_FOUND).build();
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
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(produto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
