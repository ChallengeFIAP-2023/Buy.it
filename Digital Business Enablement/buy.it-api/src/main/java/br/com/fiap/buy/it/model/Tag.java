package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @NotBlank(message = "O campo nome não pode estar vazio.")
    private String nome;

    @JsonBackReference
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
    private Set<Departamento> departamentos = new LinkedHashSet<>();
    
    @JsonBackReference
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
    private Set<Usuario> usuarios = new LinkedHashSet<>();
        
    @JsonBackReference
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
    private Set<Produto> produtos = new LinkedHashSet<>();

    public Tag addDepartamento(Departamento departamento) {
        this.departamentos.add(departamento);
        if (!departamento.getTags().contains(this)) departamento.getTags().add(this);
        return this;
    }

    public Tag removeDepartamento(Departamento departamento) {
        this.departamentos.remove(departamento);
        if (departamento.getTags().contains(this)) departamento.removeTag(this);
        return this;
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
}