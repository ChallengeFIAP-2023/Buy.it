package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.Usuario;
import br.com.fiap.domain.service.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/usuario")
public class UsuarioResource {

    private final UsuarioService service = UsuarioService.build();
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
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }

    @GET
    @Path("/cnpj/{cnpj}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCnpj(@PathParam("cnpj") String cnpj_usuario) {
        Usuario usuario = service.findByCnpj(cnpj_usuario);
        if (Objects.isNull(usuario)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Usuario usuario) {
        if (usuario == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
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
            return Response.status(Response.Status.NOT_FOUND).build();
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
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.delete(usuario);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
