package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL_USUARIO", columnNames = "EMAIL_USUARIO")
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    @Getter @Setter
    private Long id;

    @Column(name = "EMAIL_USUARIO", nullable = false)
    @NotBlank(message = "O endereço de e-mail não pode estar vazio.")
    @Email(message = "Endereço de e-mail inválido.")
    @Getter @Setter
    private String email;

    @JsonIgnore
    @Column(name = "SENHA_USUARIO", nullable = false)
    @NotBlank(message = "A senha não pode estar vazia.")
    @Getter @Setter
    private String senha;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA", foreignKey = @ForeignKey(name = "FK_PESSOA_USUARIO"))
    @Getter @Setter
    private Pessoa pessoa;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "USUARIO_TAG",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_USUARIO",
                            referencedColumnName = "ID_USUARIO",
                            foreignKey = @ForeignKey(name = "FK_USUARIO_TAG")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_TAG",
                            referencedColumnName = "ID_TAG",
                            foreignKey = @ForeignKey(name = "FK_TAG_USUARIO")
                    )
            }
    )
    private Set<Tag> tags = new LinkedHashSet<>();

    public Usuario addTag(Tag tag) {
        this.tags.add(tag);
        tag.addUsuario(this);
        return this;
    }

    public Usuario removeTag(Tag tag){
        this.tags.remove(tag);
        if (tag.getUsuarios().equals(this)) tag.removeUsuario(this);
        return this;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
}