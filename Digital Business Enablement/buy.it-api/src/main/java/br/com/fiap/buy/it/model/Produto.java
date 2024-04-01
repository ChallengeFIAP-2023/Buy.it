package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUTO")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NOME_PRODUTO",nullable = false)
    @NotBlank(message = "O campo nome não pode estar vazio.")
    private String nome;

    @Column(name = "MARCA_PRODUTO")
    private String marca;

    @Column(name = "COR_PRODUTO")
    private String cor;

    @Column(name = "TAMANHO_PRODUTO")
    private String tamanho;

    @Column(name = "MATERIAL_PRODUTO")
    private String material;

    @Column(name = "OBSERVACAO_PRODUTO", length = 500)
    private String observacao;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_DEPARTAMENTO",
            referencedColumnName = "ID_DEPARTAMENTO",
            foreignKey = @ForeignKey(name = "FK_DEPARTAMENTO_PRODUTO")
    )
    private Departamento departamento;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "PRODUTO_TAG",
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
    private Set<Tag> tags = new LinkedHashSet<>();

    public Produto addTag(Tag tag) {
        this.tags.add(tag);
        tag.addProduto(this);
        return this;
    }

    public Produto removeTag(Tag tag){
        this.tags.remove(tag);
        if (tag.getProdutos().contains(this)) tag.removeProduto(this);
        return this;
    }
}