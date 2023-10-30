package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "BUYIT_PRODUTO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_NM_PRODUTO", columnNames = "NM_PRODUTO")
})
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NM_PRODUTO", nullable = false)
    private String nome;

    @Column(name = "DS_PRODUTO")
    private String descricao;

    @Column(name = "IMG_URL_PRODUTO")
    private String imgUrl;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_CATEGORIA",
            referencedColumnName = "ID_CATEGORIA",
            foreignKey = @ForeignKey(name = "FK_CATEGORIA_PRODUTO")
    )
    private Categoria categoria;

    @Column(name = "MARCA_PRODUTO", nullable = false)
    private String marca;

    @Column(name = "MENOR_PRECO_PRODUTO", nullable = false)
    private BigDecimal menorPreco;

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, String imgUrl, Categoria categoria, String marca, BigDecimal menorPreco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imgUrl = imgUrl;
        this.categoria = categoria;
        this.marca = marca;
        this.menorPreco = menorPreco;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public Produto setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Produto setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Produto setCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public String getMarca() {
        return marca;
    }

    public Produto setMarca(String marca) {
        this.marca = marca;
        return this;
    }

    public BigDecimal getMenorPreco() {
        return menorPreco;
    }

    public Produto setMenorPreco(BigDecimal menorPreco) {
        this.menorPreco = menorPreco;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(descricao, produto.descricao) && Objects.equals(imgUrl, produto.imgUrl) && Objects.equals(categoria, produto.categoria) && Objects.equals(marca, produto.marca) && Objects.equals(menorPreco, produto.menorPreco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, imgUrl, categoria, marca, menorPreco);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", categoria=" + categoria +
                ", marca='" + marca + '\'' +
                ", menorPreco=" + menorPreco +
                '}';
    }
}
