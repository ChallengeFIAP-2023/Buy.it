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

        // CNPJ_USUARIO
        if (usuario.getCnpj_usuario() == null || usuario.getCnpj_usuario().isEmpty() || usuario.getCnpj_usuario().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O CNPJ do Usuario não pode ser NULL ou vazio");
        }

        // NM_USUARIO
        if (usuario.getNm_usuario() == null || usuario.getNm_usuario().isEmpty() || usuario.getNm_usuario().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Nome do Usuario não pode ser NULL ou vazio");
        }

        // CEP_USUARIO
        if (usuario.getCep_usuario() == null || usuario.getCep_usuario().isEmpty() || usuario.getCep_usuario().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O CEP do Usuario não pode ser NULL ou vazio");
        }

        // LOGRADOURO_USUARIO
        if (usuario.getLogradouro_usuario() == null || usuario.getLogradouro_usuario().isEmpty() || usuario.getLogradouro_usuario().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O CEP do Usuario não pode ser NULL ou vazio");
        }

        // NUM_ENDERECO_USUARIO
        if (usuario.getNum_endereco_usuario() == null || usuario.getNum_endereco_usuario().isEmpty() || usuario.getNum_endereco_usuario().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Número do Endereço do Usuario não pode ser NULL ou vazio");
        }

        // EMAIL_USUARIO
        if (usuario.getEmail_usuario() == null || usuario.getEmail_usuario().isEmpty() || usuario.getEmail_usuario().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Email do Usuario não pode ser NULL ou vazio");
        }

        // SENHA_USUARIO
        if (usuario.getSenha_usuario() == null || usuario.getSenha_usuario().isEmpty() || usuario.getSenha_usuario().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Senha do Usuario não pode ser NULL ou vazio");
        }

        // TEL_USUARIO
        if (usuario.getTel_usuario() == null || usuario.getTel_usuario().isEmpty() || usuario.getTel_usuario().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "A Senha do Usuario não pode ser NULL ou vazio");
        }

        // E_FORNECEDOR
        if (usuario.getE_fornecedor() == null) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Atributo É Fornecedor do Usuario não pode ser NULL ou vazio");
        }

        // REGIME_TRIBUTARIO_USUARIO
        if (usuario.getRegime_tributario_usuario() == null || usuario.getRegime_tributario_usuario().isEmpty() || usuario.getRegime_tributario_usuario().isBlank()) {
            return errorResponse.createErrorResponse(Response.Status.BAD_REQUEST, "O Regime Tributário do Usuario não pode ser NULL ou vazio");
        }

        // VALOR_MAX_AUTOMATICO_USUARIO
        if (usuario.getValor_max_automatico_usuario() == null) {
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
        URI location = URI.create("/usuario/" + persistedUsuario.getId_usuario());
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
        usuario.setId_usuario(existingUsuario.getId_usuario());
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
