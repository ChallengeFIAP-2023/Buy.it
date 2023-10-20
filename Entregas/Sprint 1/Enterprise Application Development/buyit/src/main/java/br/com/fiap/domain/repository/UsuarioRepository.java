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

    public List<Usuario> findByName(String name) {
        String jpql = "SELECT usuario FROM Usuario usuario  where upper(usuario.nm_usuario) LIKE CONCAT('%',upper(:nm_usuario),'%')";
        TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
        query.setParameter("nm_usuario", name);
        return query.getResultList();
    }

    public Usuario findByCnpj(String cnpj_usuario) {
        String jpql = "SELECT usuario FROM Usuario usuario WHERE usuario.cnpj_usuario = :cnpj_usuario";
        TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
        query.setParameter("cnpj_usuario", cnpj_usuario);
        return query.getSingleResult();
    }

    @Override
    public List<Usuario> findAll() {
        return manager.createQuery("FROM Usuario", Usuario.class).getResultList();
    }

    @Override
    public Usuario persist(Usuario usuario) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            usuario.setId_usuario(null);
            transaction.begin();
            usuario.setId_usuario(null);
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
            Usuario usuario_buscado = manager.find(Usuario.class, usuario.getId_usuario());
            if (Objects.nonNull(usuario_buscado)) {

                // CNPJ_USUARIO
                if (Objects.nonNull(usuario.getCnpj_usuario())) {
                    usuario_buscado.setCnpj_usuario(usuario.getCnpj_usuario());
                }

                // NM_USUARIO
                if (Objects.nonNull(usuario.getNm_usuario())) {
                    usuario_buscado.setNm_usuario(usuario.getNm_usuario());
                }

                // CEP_USUARIO
                if (Objects.nonNull(usuario.getCep_usuario())) {
                    usuario_buscado.setCep_usuario(usuario.getCep_usuario());
                }

                // LOGRADOURO_USUARIO
                if (Objects.nonNull(usuario.getLogradouro_usuario())) {
                    usuario_buscado.setLogradouro_usuario(usuario.getLogradouro_usuario());
                }

                // COMPLEMENTO_USUARIO
                usuario_buscado.setComplemento_usuario(usuario.getComplemento_usuario());

                // NUM_ENDERECO_USUARIO
                if (Objects.nonNull(usuario.getNum_endereco_usuario())) {
                    usuario_buscado.setNum_endereco_usuario(usuario.getNum_endereco_usuario());
                }

                // EMAIL_USUARIO
                if (Objects.nonNull(usuario.getEmail_usuario())) {
                    usuario_buscado.setEmail_usuario(usuario.getEmail_usuario());
                }

                // SENHA_USUARIO
                if (Objects.nonNull(usuario.getSenha_usuario())) {
                    usuario_buscado.setSenha_usuario(usuario.getSenha_usuario());
                }

                // TEL_USUARIO
                if (Objects.nonNull(usuario.getTel_usuario())) {
                    usuario_buscado.setTel_usuario(usuario.getTel_usuario());
                }

                // E_FORNECEDOR
                if (Objects.nonNull(usuario.getE_fornecedor())) {
                    usuario_buscado.setE_fornecedor(usuario.getE_fornecedor());
                }

                // IMG_URL_USUARIO
                usuario_buscado.setImg_url_usuario(usuario.getImg_url_usuario());

                // REGIME_TRIBUTARIO_USUARIO
                if (Objects.nonNull(usuario.getRegime_tributario_usuario())) {
                    usuario_buscado.setRegime_tributario_usuario(usuario.getRegime_tributario_usuario());
                }

                // VALOR_MAX_AUTOMATICO_USUARIO
                if (Objects.nonNull(usuario.getValor_max_automatico_usuario())) {
                    usuario_buscado.setValor_max_automatico_usuario(usuario.getValor_max_automatico_usuario());
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
