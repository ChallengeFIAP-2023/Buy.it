package br.com.fiap.domain.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "BUYIT_TAG", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_NM_TAG", columnNames = "NM_TAG")
})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TAG")
    @SequenceGenerator(name = "SQ_TAG", sequenceName = "SQ_TAG", allocationSize = 1)
    @Column(name = "ID_TAG")
    private Long id;

    @Column(name = "NM_TAG", nullable = false)
    private String nome;

    @JsonbTransient
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BUYIT_USUARIO_TAG",
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

    public Tag() {
    }

    public Tag(Long id, String nome, Set<Usuario> usuarios) {
        this.id = id;
        this.nome = nome;
        this.usuarios = Objects.nonNull(usuarios) ? usuarios : new LinkedHashSet<>();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return Objects.equals(id, tag.id) && Objects.equals(nome, tag.nome) && Objects.equals(usuarios, tag.usuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuarios);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
