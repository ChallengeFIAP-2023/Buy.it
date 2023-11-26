package br.com.fiap.buy.it.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TAG", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NOME_TAG", columnNames = "NOME_TAG")
})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TAG")
    @SequenceGenerator(name = "SQ_TAG", sequenceName = "SQ_TAG", allocationSize = 1)
    @Column(name = "ID_TAG")
    private Long id;

    @Column(name = "NOME_TAG", nullable = false)
    @NotBlank(message = "O nome da tag não pode estar vazio.")
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TAG_DEPARTAMENTO",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_TAG",
                            referencedColumnName = "ID_TAG",
                            foreignKey = @ForeignKey(name = "FK_TAG_DEPARTAMENTO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_DEPARTAMENTO",
                            referencedColumnName = "ID_DEPARTAMENTO",
                            foreignKey = @ForeignKey(name = "FK_DEPARTAMENTO_TAG")
                    )
            }
    )
    private Set<Departamento> departamentos;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "USUARIO_TAG",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_TAG",
                            referencedColumnName = "ID_TAG",
                            foreignKey = @ForeignKey(name = "FK_TAG_USUARIO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_USUARIO",
                            referencedColumnName = "ID_USUARIO",
                            foreignKey = @ForeignKey(name = "FK_USUARIO_TAG")
                    )
            }
    )
    private Set<Usuario> usuarios;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "PRODUTO_TAG",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_TAG",
                            referencedColumnName = "ID_TAG",
                            foreignKey = @ForeignKey(name = "FK_TAG_PRODUTO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_PRODUTO",
                            referencedColumnName = "ID_PRODUTO",
                            foreignKey = @ForeignKey(name = "FK_PRODUTO_TAG")
                    )
            }
    )
    private Set<Produto> produtos;

    public Long getId() {
        return id;
    }

    public Tag setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Tag setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Tag addDepartamento(Departamento departamento) {
        this.departamentos.add(departamento);
        departamento.addTag(this);
        return this;
    }

    public Tag removeDepartamento(Departamento departamento) {
        this.departamentos.remove(departamento);
        if (departamento.getTags().equals(this)) departamento.removeTag(this);
        return this;
    }


    public Set<Departamento> getDepartamentos() {
        return Collections.unmodifiableSet(departamentos);
    }

    public Tag addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.addTag(this);
        return this;
    }

    public Tag removeUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
        if (usuario.getTags().equals(this)) usuario.removeTag(this);
        return this;
    }

    public Set<Usuario> getUsuarios() {
        return Collections.unmodifiableSet(usuarios);
    }

    public Tag addProduto(Produto produto) {
        this.produtos.add(produto);
        produto.addTag(this);
        return this;
    }

    public Tag removeProduto(Produto produto) {
        this.produtos.remove(produto);
        if (produto.getTags().equals(this)) produto.removeTag(this);
        return this;
    }


    public Set<Produto> getProdutos() {
        return Collections.unmodifiableSet(produtos);
    }
}
