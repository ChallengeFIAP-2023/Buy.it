package br.com.fiap.domain.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "BUYIT_TAG", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_NOME_TAG", columnNames = "NOME_TAG")
})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_TAG")
    @SequenceGenerator(name = "BUYIT_SQ_TAG", sequenceName = "BUYIT_SQ_TAG", allocationSize = 1)
    @Column(name = "ID_TAG")
    private Long id;

    @Column(name = "NOME_TAG", nullable = false)
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BUYIT_TAG_DEPARTAMENTO",
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

    public Tag(Long id, String nome, Set<Departamento> departamentos, Set<Usuario> usuarios) {
        this.id = id;
        this.nome = nome;
        this.departamentos = Objects.nonNull(departamentos) ? departamentos : new LinkedHashSet<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;

        if (!id.equals(tag.id)) return false;
        if (!nome.equals(tag.nome)) return false;
        if (!Objects.equals(departamentos, tag.departamentos)) return false;
        return Objects.equals(usuarios, tag.usuarios);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + (departamentos != null ? departamentos.hashCode() : 0);
        result = 31 * result + (usuarios != null ? usuarios.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
