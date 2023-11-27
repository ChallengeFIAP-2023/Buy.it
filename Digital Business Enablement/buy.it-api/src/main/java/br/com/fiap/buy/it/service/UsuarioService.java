package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.LoginDTO;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> listAll(Pageable pageRequest) {
        return usuarioRepository.findAll(pageRequest);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Usuario) não encontrado(a) por ID: " + id));
    }

    public Usuario create(Usuario newData) {
        return usuarioRepository.save(newData);
    }

    public Usuario update(Long id, Usuario updatedData) {
        findById(id);
        updatedData.setId(id);
        return usuarioRepository.save(updatedData);
    }

    public void delete(Long id) {
        usuarioRepository.delete(findById(id));
    }

    public Optional<Usuario> validarLogin(LoginDTO loginDTO) {
        return usuarioRepository.findByEmail(loginDTO.getEmail())
                .filter(usuario -> usuario.getSenha().equals(loginDTO.getSenha()));
    }
}