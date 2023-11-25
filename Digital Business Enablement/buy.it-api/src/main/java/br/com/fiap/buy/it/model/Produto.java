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
@Table(name = "PRODUTO")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NOME_PRODUTO",nullable = false)
    @NotBlank(message = "O nome do produto não pode estar vazio.")
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

    public Long getId() {
        return id;
    }

    public Produto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Produto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getMarca() {
        return marca;
    }

    public Produto setMarca(String marca) {
        this.marca = marca;
        return this;
    }

    public String getCor() {
        return cor;
    }

    public Produto setCor(String cor) {
        this.cor = cor;
        return this;
    }

    public String getTamanho() {
        return tamanho;
    }

    public Produto setTamanho(String tamanho) {
        this.tamanho = tamanho;
        return this;
    }

    public String getMaterial() {
        return material;
    }

    public Produto setMaterial(String material) {
        this.material = material;
        return this;
    }

    public String getObservacao() {
        return observacao;
    }

    public Produto setObservacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Produto setDepartamento(Departamento departamento) {
        this.departamento = departamento;
        return this;
    }

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

