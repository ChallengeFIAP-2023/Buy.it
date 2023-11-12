package br.com.fiap.domain.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "BUYIT_ESTOQUE")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ESTOQUE")
    @SequenceGenerator(name = "SQ_ESTOQUE", sequenceName = "SQ_ESTOQUE", allocationSize = 1)
    @Column(name = "ID_ESTOQUE")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PRODUTO",
            referencedColumnName = "ID_PRODUTO",
            foreignKey = @ForeignKey(name = "FK_PRODUTO_ESTOQUE"),
            nullable = false
    )
    private Produto produto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_VALOR_VARIACAO",
            referencedColumnName = "ID_VALOR_VARIACAO",
            foreignKey = @ForeignKey(name = "FK_VALOR_VARIACAO_ESTOQUE"),
            nullable = false
    )
    private ValorVariacao valorVariacao;

    @Column(name = "QTD_ESTOQUE", nullable = false)
    private Long quantidade;

    @Column(name = "PRECO_UNITARIO", nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "IMG_URL_ESTOQUE")
    private String imgUrl;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_ESTOQUE"),
            nullable = false
    )
    private Usuario usuario;

    @JsonbTransient
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BUYIT_PEDIDO_ESTOQUE",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_ESTOQUE",
                            referencedColumnName = "ID_ESTOQUE",
                            foreignKey = @ForeignKey(name = "FK_ESTOQUE_PEDIDO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_PEDIDO",
                            referencedColumnName = "ID_PEDIDO",
                            foreignKey = @ForeignKey(name = "FK_PEDIDO_ESTOQUE")
                    )
            }
    )
    private Set<Pedido> pedidos;

    public Estoque() {
    }

    public Estoque(Long id, Produto produto, ValorVariacao valorVariacao, Long quantidade, BigDecimal precoUnitario, String imgUrl, Usuario usuario, Set<Pedido> pedidos) {
        this.id = id;
        this.produto = produto;
        this.valorVariacao = valorVariacao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.imgUrl = imgUrl;
        this.usuario = usuario;
        this.pedidos = Objects.nonNull(pedidos) ? pedidos : new LinkedHashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Estoque setId(Long id) {
        this.id = id;
        return this;
    }

    public Produto getProduto() {
        return produto;
    }

    public Estoque setProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public ValorVariacao getValorVariacao() {
        return valorVariacao;
    }

    public Estoque setValorVariacao(ValorVariacao valorVariacao) {
        this.valorVariacao = valorVariacao;
        return this;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public Estoque setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Estoque setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Estoque setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Estoque setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Estoque addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        pedido.addEstoque(this);
        return this;
    }

    public Estoque removePedido(Pedido pedido) {
        this.pedidos.remove(pedido);
        if (pedido.getEstoques().equals(this)) pedido.removeEstoque(this);
        return this;
    }

    public Set<Pedido> getPedidos() {
        return Collections.unmodifiableSet(pedidos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estoque estoque)) return false;
        return Objects.equals(id, estoque.id) && Objects.equals(produto, estoque.produto) && Objects.equals(valorVariacao, estoque.valorVariacao) && Objects.equals(quantidade, estoque.quantidade) && Objects.equals(precoUnitario, estoque.precoUnitario) && Objects.equals(imgUrl, estoque.imgUrl) && Objects.equals(usuario, estoque.usuario) && Objects.equals(pedidos, estoque.pedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produto, valorVariacao, quantidade, precoUnitario, imgUrl, usuario, pedidos);
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", produto=" + produto +
                ", valorVariacao=" + valorVariacao +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", imgUrl='" + imgUrl + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}