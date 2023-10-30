package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class UsuarioRepository implements Repository<Usuario, Long> {

    private static volatile UsuarioRepository instance;
    private final EntityManager manager;

    private UsuarioRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static UsuarioRepository build(EntityManager manager) {
        UsuarioRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (UsuarioRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new UsuarioRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public Usuario findById(Long id) {
        return manager.find(Usuario.class, id);
    }

    public List<Usuario> findByName(String nome) {
        String jpql = "SELECT usuario FROM Usuario usuario  where upper(usuario.nome) LIKE CONCAT('%',upper(:nome),'%')";
        TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    public Usuario findByCnpj(String cnpj) {
        String cnpjNumerico = cnpj.replaceAll("[^0-9]", "");

        if (cnpjNumerico.length() == 14) {
            String cnpjFormatado = cnpjNumerico.substring(0, 2) + "." +
                    cnpjNumerico.substring(2, 5) + "." +
                    cnpjNumerico.substring(5, 8) + "/" +
                    cnpjNumerico.substring(8, 12) + "-" +
                    cnpjNumerico.substring(12);

            String jpql = "SELECT usuario FROM Usuario usuario WHERE usuario.cnpj = :cnpj";
            TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
            query.setParameter("cnpj", cnpjFormatado);

            try {
                return query.getSingleResult();
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Usuario> findAll() {
        return manager.createQuery("FROM Usuario", Usuario.class).getResultList();
    }

    @Override
    public Usuario persist(Usuario usuario) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            usuario.setId(null);
            transaction.begin();
            manager.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Usuario usuario_buscado = manager.find(Usuario.class, usuario.getId());
            if (Objects.nonNull(usuario_buscado)) {

                // CNPJ_USUARIO
                if (Objects.nonNull(usuario.getCnpj())) {
                    usuario_buscado.setCnpj(usuario.getCnpj());
                }

                // NM_USUARIO
                if (Objects.nonNull(usuario.getNome())) {
                    usuario_buscado.setNome(usuario.getNome());
                }

                // CEP_USUARIO
                if (Objects.nonNull(usuario.getCep())) {
                    usuario_buscado.setCep(usuario.getCep());
                }

                // LOGRADOURO_USUARIO
                if (Objects.nonNull(usuario.getLogradouro())) {
                    usuario_buscado.setLogradouro(usuario.getLogradouro());
                }

                // COMPLEMENTO_USUARIO
                usuario_buscado.setComplemento(usuario.getComplemento());

                // NUM_ENDERECO_USUARIO
                if (Objects.nonNull(usuario.getNumEndereco())) {
                    usuario_buscado.setNumEndereco(usuario.getNumEndereco());
                }

                // EMAIL_USUARIO
                if (Objects.nonNull(usuario.getEmail())) {
                    usuario_buscado.setEmail(usuario.getEmail());
                }

                // SENHA_USUARIO
                if (Objects.nonNull(usuario.getSenha())) {
                    usuario_buscado.setSenha(usuario.getSenha());
                }

                // TEL_USUARIO
                if (Objects.nonNull(usuario.getTel())) {
                    usuario_buscado.setTel(usuario.getTel());
                }

                // E_FORNECEDOR
                if (Objects.nonNull(usuario.getE_fornecedor())) {
                    usuario_buscado.setE_fornecedor(usuario.getE_fornecedor());
                }

                // IMG_URL_USUARIO
                usuario_buscado.setImgUrl(usuario.getImgUrl());

                // REGIME_TRIBUTARIO_USUARIO
                if (Objects.nonNull(usuario.getRegimeTributario())) {
                    usuario_buscado.setRegimeTributario(usuario.getRegimeTributario());
                }

                // VALOR_MAX_AUTOMATICO_USUARIO
                if (Objects.nonNull(usuario.getCompraAutomatica())) {
                    usuario_buscado.setCompraAutomatica(usuario.getCompraAutomatica());
                }

                usuario = manager.merge(usuario_buscado);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return usuario;
    }

    @Override
    public boolean delete(Usuario usuario) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(usuario);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }
}
