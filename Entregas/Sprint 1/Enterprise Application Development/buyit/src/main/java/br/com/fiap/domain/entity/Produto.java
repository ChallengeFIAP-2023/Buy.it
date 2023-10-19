package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "PRODUTO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_PRODUTO", columnNames = "NM_PRODUTO")
})
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Long id_produto;

    @Column(name = "NM_PRODUTO", nullable = false)
    private String nm_produto;

    @Column(name = "DS_PRODUTO")
    private String ds_produto;

    @Column(name = "IMG_URL_PRODUTO")
    private String img_url_produto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_CATEGORIA",
            referencedColumnName = "ID_CATEGORIA",
            foreignKey = @ForeignKey(name = "FK_CATEGORIA_PRODUTO")
    )
    private Categoria id_categoria;

    @Column(name = "MENOR_PRECO_PRODUTO", nullable = false)
    private BigDecimal menor_preco_produto;

    public Produto() {
    }

    public Produto(Long id_produto, String nm_produto, String ds_produto, String img_url_produto, Categoria id_categoria, BigDecimal menor_preco_produto) {
        this.id_produto = id_produto;
        this.nm_produto = nm_produto;
        this.ds_produto = ds_produto;
        this.img_url_produto = img_url_produto;
        this.id_categoria = id_categoria;
        this.menor_preco_produto = menor_preco_produto;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public Produto setId_produto(Long id_produto) {
        this.id_produto = id_produto;
        return this;
    }

    public String getNm_produto() {
        return nm_produto;
    }

    public Produto setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;
        return this;
    }

    public String getDs_produto() {
        return ds_produto;
    }

    public Produto setDs_produto(String ds_produto) {
        this.ds_produto = ds_produto;
        return this;
    }

    public String getImg_url_produto() {
        return img_url_produto;
    }

    public Produto setImg_url_produto(String img_url_produto) {
        this.img_url_produto = img_url_produto;
        return this;
    }

    public Categoria getId_categoria() {
        return id_categoria;
    }

    public Produto setId_categoria(Categoria id_categoria) {
        this.id_categoria = id_categoria;
        return this;
    }

    public BigDecimal getMenor_preco_produto() {
        return menor_preco_produto;
    }

    public Produto setMenor_preco_produto(BigDecimal menor_preco_produto) {
        this.menor_preco_produto = menor_preco_produto;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;

        if (!id_produto.equals(produto.id_produto)) return false;
        if (!nm_produto.equals(produto.nm_produto)) return false;
        if (!Objects.equals(ds_produto, produto.ds_produto)) return false;
        if (!Objects.equals(img_url_produto, produto.img_url_produto))
            return false;
        if (!Objects.equals(id_categoria, produto.id_categoria))
            return false;
        return menor_preco_produto.equals(produto.menor_preco_produto);
    }

    @Override
    public int hashCode() {
        int result = id_produto.hashCode();
        result = 31 * result + nm_produto.hashCode();
        result = 31 * result + (ds_produto != null ? ds_produto.hashCode() : 0);
        result = 31 * result + (img_url_produto != null ? img_url_produto.hashCode() : 0);
        result = 31 * result + (id_categoria != null ? id_categoria.hashCode() : 0);
        result = 31 * result + menor_preco_produto.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id_produto=" + id_produto +
                ", nm_produto='" + nm_produto + '\'' +
                ", ds_produto='" + ds_produto + '\'' +
                ", img_url_produto='" + img_url_produto + '\'' +
                ", id_categoria=" + id_categoria +
                ", menor_preco_produto=" + menor_preco_produto +
                '}';
    }
}
