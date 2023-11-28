package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "PRODUTO")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    @Getter @Setter
    private Long id;

    @Column(name = "NOME_PRODUTO",nullable = false)
    @NotBlank(message = "O nome do produto não pode estar vazio.")
    @Getter @Setter
    private String nome;

    @Column(name = "MARCA_PRODUTO")
    @Getter @Setter
    private String marca;

    @Column(name = "COR_PRODUTO")
    @Getter @Setter
    private String cor;

    @Column(name = "TAMANHO_PRODUTO")
    @Getter @Setter
    private String tamanho;

    @Column(name = "MATERIAL_PRODUTO")
    @Getter @Setter
    private String material;

    @Column(name = "OBSERVACAO_PRODUTO", length = 500)
    @Getter @Setter
    private String observacao;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_DEPARTAMENTO",
            referencedColumnName = "ID_DEPARTAMENTO",
            foreignKey = @ForeignKey(name = "FK_DEPARTAMENTO_PRODUTO")
    )
    @Getter @Setter
    private Departamento departamento;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TAG_PRODUTO",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_PRODUTO",
                            referencedColumnName = "ID_PRODUTO",
                            foreignKey = @ForeignKey(name = "FK_PRODUTO_TAG")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_TAG",
                            referencedColumnName = "ID_TAG",
                            foreignKey = @ForeignKey(name = "FK_TAG_PRODUTO")
                    )
            }
    )
    private Set<Tag> tags;

    public Produto addTag(Tag tag) {
        this.tags.add(tag);
        tag.addProduto(this);
        return this;
    }

    public Produto removeTag(Tag tag){
        this.tags.remove(tag);
        if (tag.getProdutos().equals(this)) tag.removeProduto(this);
        return this;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
}