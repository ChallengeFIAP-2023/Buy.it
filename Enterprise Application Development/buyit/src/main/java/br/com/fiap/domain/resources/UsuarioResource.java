package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Usuario;
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

@Path("/usuario")
public class UsuarioResource implements Resource<Usuario, Long> {

    private final UsuarioService service = UsuarioService.build();

    CustomErrorResponse errorResponse = new CustomErrorResponse();

    private Response validateUsuario(Usuario usuario) {
        // USUARIO
        if (usuario == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Usuario não pode ser NULL");
        }

        // CNPJ
        if (usuario.getCnpj() == null || usuario.getCnpj().isEmpty() || usuario.getCnpj().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O CNPJ do Usuario não pode ser NULL ou vazio");
        }

        // NOME
        if (usuario.getNome() == null || usuario.getNome().isEmpty() || usuario.getNome().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Nome do Usuario não pode ser NULL ou vazio");
        }

        // CEP
        if (usuario.getCep() == null || usuario.getCep().isEmpty() || usuario.getCep().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O CEP do Usuario não pode ser NULL ou vazio");
        }

        // LOGRADOURO
        if (usuario.getLogradouro() == null || usuario.getLogradouro().isEmpty() || usuario.getLogradouro().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O CEP do Usuario não pode ser NULL ou vazio");
        }

        // NUM_ENDERECO
        if (usuario.getNumEndereco() == null || usuario.getNumEndereco().isEmpty() || usuario.getNumEndereco().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Número do Endereço do Usuario não pode ser NULL ou vazio");
        }

        // EMAIL
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty() || usuario.getEmail().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Email do Usuario não pode ser NULL ou vazio");
        }

        // SENHA
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty() || usuario.getSenha().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Senha do Usuario não pode ser NULL ou vazio");
        }

        // TEL
        if (usuario.getTel() == null || usuario.getTel().isEmpty() || usuario.getTel().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Senha do Usuario não pode ser NULL ou vazio");
        }

        // E_FORNECEDOR
        if (usuario.getE_fornecedor() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Atributo É Fornecedor do Usuario não pode ser NULL ou vazio");
        }

        // REGIME_TRIBUTARIO
        if (usuario.getRegimeTributario() == null || usuario.getRegimeTributario().isEmpty() || usuario.getRegimeTributario().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Regime Tributário do Usuario não pode ser NULL ou vazio");
        }

        // COMPRA_AUTOMATICA
        if (usuario.getCompraAutomatica() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Valor Máximo Automático do Usuario não pode ser NULL ou vazio");
        }

        return null;
    }

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Usuario> usuarios = service.findAll();
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Usuario usuario = service.findById(id);
        if (Objects.isNull(usuario)) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Usuario de ID: " + id + " não encontrado");
        }
        return Response.ok(usuario).build();
    }

    @GET
    @Path("/cnpj/{cnpj}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCnpj(@PathParam("cnpj") String cnpj_usuario) {
        Usuario usuario = service.findByCnpj(cnpj_usuario);
        if (Objects.isNull(usuario)) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Usuario de CNPJ: " + cnpj_usuario + " não encontrado");
        }
        return Response.ok(usuario).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persist(Usuario usuario) {
        Response validationResponse = validateUsuario(usuario);
        if (validationResponse != null) {
            return validationResponse;
        }
        Usuario persistedUsuario = service.persist(usuario);
        URI location = URI.create("/usuario/" + persistedUsuario.getId());
        return Response.created(location).entity(persistedUsuario).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Usuario usuario) {
        Usuario existingUsuario = service.findById(id);
        if (existingUsuario == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Usuario de ID: " + id + " não encontrado");
        }
        Response validationResponse = validateUsuario(usuario);
        if (validationResponse != null) {
            return validationResponse;
        }
        usuario.setId(existingUsuario.getId());
        Usuario updatedUsuario = service.update(usuario);
        return Response.ok(updatedUsuario).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Usuario usuario = service.findById(id);
        if (usuario == null) {
            return errorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Usuario de ID: " + id + " não encontrado");
        }
        service.delete(usuario);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
