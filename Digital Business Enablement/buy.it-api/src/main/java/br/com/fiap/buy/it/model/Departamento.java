package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
    @Getter @Setter
    private Long id;

    @Column(name = "NOME_DEPARTAMENTO", nullable = false)
    @NotBlank(message = "O nome do departamento não pode estar vazio.")
    @Getter @Setter
    private String nome;

    @Column(name = "ICONE_DEPARTAMENTO")
    @Getter @Setter
    private String icone;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "TAG_DEPARTAMENTO", joinColumns = {
            @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO", foreignKey = @ForeignKey(name = "FK_DEPARTAMENTO_TAG"))
    }, inverseJoinColumns = {
            @JoinColumn(name = "ID_TAG", referencedColumnName = "ID_TAG", foreignKey = @ForeignKey(name = "FK_TAG_DEPARTAMENTO"))
    })
    private Set<Tag> tags;

    public Optional<Departamento> addTag(Tag tag) {
        if (this.tags == null)
            this.tags = new LinkedHashSet<>();
        this.tags.add(tag);
        tag.addDepartamento(this);
        return Optional.of(this);
    }

    public Departamento removeTag(Tag tag) {
        this.tags.remove(tag);
        if (tag.getDepartamentos().equals(this))
            tag.removeDepartamento(this);
        return this;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
}