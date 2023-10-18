package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "ESTOQUE")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ESTOQUE")
    @SequenceGenerator(name = "SQ_ESTOQUE", sequenceName = "SQ_ESTOQUE", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_ESTOQUE")
    private Long id_estoque;

    @ManyToOne
    @JoinColumn(
            name = "ID_PRODUTO",
            referencedColumnName = "ID_PRODUTO",
            foreignKey = @ForeignKey(name = "FK_PRODUTO_ESTOQUE"),
            nullable = false
    )
    private Produto id_produto;

    @ManyToOne
    @JoinColumn(
            name = "ID_VARIACAO",
            referencedColumnName = "ID_VARIACAO",
            foreignKey = @ForeignKey(name = "FK_VARIACAO_ESTOQUE"),
            nullable = false
    )
    private Variacao id_variacao_produto;

    @Column(name = "QTD_ESTOQUE", nullable = false)
    private Long qtd_estoque;

    @Column(name = "PRECO", nullable = false)
    private BigDecimal preco;

    @Column(name = "IMG_URL_ESTOQUE")
    private String img_url_estoque;

    @ManyToOne
    @JoinColumn(
            name = "ID_USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_ESTOQUE"),
            nullable = false
    )
    private Usuario id_usuario;

    public Estoque() {
    }

    public Estoque(Long id_estoque, Produto id_produto, Variacao id_variacao_produto, Long qtd_estoque, BigDecimal preco, String img_url_estoque, Usuario id_usuario) {
        this.id_estoque = id_estoque;
        this.id_produto = id_produto;
        this.id_variacao_produto = id_variacao_produto;
        this.qtd_estoque = qtd_estoque;
        this.preco = preco;
        this.img_url_estoque = img_url_estoque;
        this.id_usuario = id_usuario;
    }

    public Long getId_estoque() {
        return id_estoque;
    }

    public Estoque setId_estoque(Long id_estoque) {
        this.id_estoque = id_estoque;
        return this;
    }

    public Produto getId_produto() {
        return id_produto;
    }

    public Estoque setId_produto(Produto id_produto) {
        this.id_produto = id_produto;
        return this;
    }

    public Variacao getId_variacao_produto() {
        return id_variacao_produto;
    }

    public Estoque setId_variacao_produto(Variacao id_variacao_produto) {
        this.id_variacao_produto = id_variacao_produto;
        return this;
    }

    public Long getQtd_estoque() {
        return qtd_estoque;
    }

    public Estoque setQtd_estoque(Long qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
        return this;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Estoque setPreco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }

    public String getImg_url_estoque() {
        return img_url_estoque;
    }

    public Estoque setImg_url_estoque(String img_url_estoque) {
        this.img_url_estoque = img_url_estoque;
        return this;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public Estoque setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estoque estoque)) return false;

        if (!id_estoque.equals(estoque.id_estoque)) return false;
        if (!id_produto.equals(estoque.id_produto)) return false;
        if (!id_variacao_produto.equals(estoque.id_variacao_produto)) return false;
        if (!qtd_estoque.equals(estoque.qtd_estoque)) return false;
        if (!preco.equals(estoque.preco)) return false;
        if (!Objects.equals(img_url_estoque, estoque.img_url_estoque))
            return false;
        return id_usuario.equals(estoque.id_usuario);
    }

    @Override
    public int hashCode() {
        int result = id_estoque.hashCode();
        result = 31 * result + id_produto.hashCode();
        result = 31 * result + id_variacao_produto.hashCode();
        result = 31 * result + qtd_estoque.hashCode();
        result = 31 * result + preco.hashCode();
        result = 31 * result + (img_url_estoque != null ? img_url_estoque.hashCode() : 0);
        result = 31 * result + id_usuario.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id_estoque=" + id_estoque +
                ", id_produto=" + id_produto +
                ", id_variacao_produto=" + id_variacao_produto +
                ", qtd_estoque=" + qtd_estoque +
                ", preco=" + preco +
                ", img_url_estoque='" + img_url_estoque + '\'' +
                ", id_usuario=" + id_usuario +
                '}';
    }
}
