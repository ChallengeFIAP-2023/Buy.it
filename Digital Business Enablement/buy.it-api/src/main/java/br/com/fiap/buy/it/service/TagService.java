package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.TagRepository;
import br.com.fiap.buy.it.repository.DepartamentoRepository;
import br.com.fiap.buy.it.repository.ProdutoRepository;
import br.com.fiap.buy.it.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Tag> listAll(Pageable pageRequest) {
        return tagRepository.findAll(pageRequest);
    }

    public Tag findById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Tag) não encontrado(a) por ID: " + id));
    }

    public Tag create(Tag newData) {
        validateDepartamentos(newData.getDepartamentos());
        validateProdutos(newData.getProdutos());
        validateUsuarios(newData.getUsuarios());
        return tagRepository.save(newData);
    }

    public Tag update(Long id, Tag updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(Tag) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        validateDepartamentos(updatedData.getDepartamentos());
        validateProdutos(updatedData.getProdutos());
        validateUsuarios(updatedData.getUsuarios());
        return tagRepository.save(updatedData);
    }

    public void delete(Long id) {
        tagRepository.delete(findById(id));
    }

    private void validateDepartamentos(Set<Departamento> departamentos) {
        for (Departamento departamento : departamentos) {
            departamentoRepository.findById(departamento.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "(Tag) - Departamento não encontrada por ID: " + departamento.getId()));
        }
    }

    private void validateProdutos(Set<Produto> produtos) {
        for (Produto produto : produtos) {
            produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "(Tag) - Produto não encontrada por ID: " + produto.getId()));
        }
    }

    private void validateUsuarios(Set<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "(Tag) - Usuario não encontrada por ID: " + usuario.getId()));
        }
    }
}