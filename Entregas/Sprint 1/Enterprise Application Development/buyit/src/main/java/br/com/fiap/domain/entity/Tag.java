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
    @Column(name = "ID_TAG", columnDefinition = "NUMBER(8)")
    private Long id_tag;

    @Column(name = "NM_TAG", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String nm_tag;

    @JsonbTransient
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BUYIT_USUARIO_TAG",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_TAG",
                            columnDefinition = "NUMBER(8)",
                            referencedColumnName = "ID_TAG",
                            foreignKey = @ForeignKey(name = "FK_TAG_USUARIO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_USUARIO",
                            columnDefinition = "NUMBER(8)",
                            referencedColumnName = "ID_USUARIO",
                            foreignKey = @ForeignKey(name = "FK_USUARIO_TAG")
                    )
            }
    )
    private Set<Usuario> usuarios;

    public Tag() {
    }

    public Tag(Long id_tag, String nm_tag, Set<Usuario> usuarios) {
        this.id_tag = id_tag;
        this.nm_tag = nm_tag;
        this.usuarios = Objects.nonNull(usuarios) ? usuarios : new LinkedHashSet<>();
    }

    public Long getId_tag() {
        return id_tag;
    }

    public Tag setId_tag(Long id_tag) {
        this.id_tag = id_tag;
        return this;
    }

    public String getNm_tag() {
        return nm_tag;
    }

    public Tag setNm_tag(String nm_tag) {
        this.nm_tag = nm_tag;
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
        return Objects.equals(id_tag, tag.id_tag) && Objects.equals(nm_tag, tag.nm_tag) && Objects.equals(usuarios, tag.usuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_tag, nm_tag, usuarios);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id_tag=" + id_tag +
                ", nm_tag='" + nm_tag + '\'' +
                '}';
    }
}
