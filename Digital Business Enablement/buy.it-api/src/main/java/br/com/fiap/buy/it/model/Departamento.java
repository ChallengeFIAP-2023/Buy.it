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
@Table(name = "DEPARTAMENTO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NOME_DEPARTAMENTO", columnNames = "NOME_DEPARTAMENTO")
})
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DEPARTAMENTO")
    @SequenceGenerator(name = "SQ_DEPARTAMENTO", sequenceName = "SQ_DEPARTAMENTO", allocationSize = 1)
    @Column(name = "ID_DEPARTAMENTO")
    private Long id;

    @Column(name = "NOME_DEPARTAMENTO", nullable = false)
    @NotBlank(message = "O nome do departamento não pode estar vazio.")
    private String nome;

    @Column(name = "ICONE_DEPARTAMENTO")
    private String icone;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TAG_DEPARTAMENTO",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_DEPARTAMENTO",
                            referencedColumnName = "ID_DEPARTAMENTO",
                            foreignKey = @ForeignKey(name = "FK_DEPARTAMENTO_TAG")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_TAG",
                            referencedColumnName = "ID_TAG",
                            foreignKey = @ForeignKey(name = "FK_TAG_DEPARTAMENTO")
                    )
            }
    )
    private Set<Tag> tags;

    public Long getId() {
        return id;
    }

    public Departamento setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Departamento setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getIcone() {
        return icone;
    }

    public Departamento setIcone(String icone) {
        this.icone = icone;
        return this;
    }

    public Departamento addTag(Tag tag) {
        this.tags.add(tag);
        tag.addDepartamento(this);
        return this;
    }

    public Departamento removeTag(Tag tag) {
        this.tags.remove(tag);
        if (tag.getDepartamentos().equals(this)) tag.removeDepartamento(this);
        return this;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
}
