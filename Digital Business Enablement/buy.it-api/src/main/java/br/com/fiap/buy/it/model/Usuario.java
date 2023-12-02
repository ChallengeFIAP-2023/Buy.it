package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL_USUARIO", columnNames = "EMAIL_USUARIO"),
        @UniqueConstraint(name = "UK_CNPJ_PJ", columnNames = "CNPJ_PJ")
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    @Getter @Setter
    private Long id;

    @Column(name = "EMAIL_USUARIO", nullable = false)
    @NotBlank(message = "O campo email não pode estar vazio.")
    @Email(message = "Endereço de e-mail inválido.")
    @Getter @Setter
    private String email;

    @JsonIgnore
    @Column(name = "SENHA_USUARIO", nullable = false)
    @NotBlank(message = "O campo senha não pode estar vazio.")
    @Getter @Setter
    private String senha;

    @Column(name = "NOME_PESSOA", nullable = false)
    @NotBlank(message = "O campo nome não pode estar vazio.")
    @Getter @Setter
    private String nome;

    @Column(name = "IMAGEM_PESSOA")
    @Getter @Setter
    private String urlImagem;

    @Column(name = "CNPJ_PJ", nullable = false)
    @NotBlank(message = "O campo cnpj não pode estar vazio.")
    @Getter @Setter
    private String cnpj;

    @Column(name = "IS_FORNECEDOR_PJ", nullable = false)
    @NotNull(message = "O campo isFornecedor não pode estar vazio.")
    @Getter @Setter
    private Boolean isFornecedor;

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
}