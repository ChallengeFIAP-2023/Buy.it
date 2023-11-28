package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.dto.LoginDTO;
import br.com.fiap.buy.it.model.Pessoa;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.repository.UsuarioRepository;
import br.com.fiap.buy.it.repository.PessoaRepository;
import br.com.fiap.buy.it.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TagRepository tagRepository;

    public Page<Usuario> listAll(Pageable pageRequest) {
        return usuarioRepository.findAll(pageRequest);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Usuario) não encontrado(a) por ID: " + id));
    }

    public Usuario create(Usuario newData) {
        Pessoa pessoa = pessoaRepository.findById(newData.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Usuario) - Pessoa não encontrado(a) por ID: " + newData.getPessoa().getId()));
        newData.setPessoa(pessoa);
        validateTags(newData.getTags());
        return usuarioRepository.save(newData);
    }

    public Usuario update(Long id, Usuario updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(Usuario) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        Pessoa pessoa = pessoaRepository.findById(updatedData.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Usuario) - Pessoa não encontrado(a) por ID: " + updatedData.getPessoa().getId()));
        updatedData.setPessoa(pessoa);
        validateTags(updatedData.getTags());
        return usuarioRepository.save(updatedData);
    }

    public void delete(Long id) {
        usuarioRepository.delete(findById(id));
    }

    private void validateTags(Set<Tag> tags) {
        for (Tag tag : tags) {
            tagRepository.findById(tag.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "(Usuario) - Tag não encontrada por ID: " + tag.getId()));
        }
    }

    public Optional<Usuario> validarLogin(LoginDTO loginDTO) {
        return usuarioRepository.findByEmail(loginDTO.getEmail())
                .filter(usuario -> usuario.getSenha().equals(loginDTO.getSenha()));
    }
}