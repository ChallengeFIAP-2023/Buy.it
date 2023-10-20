package br.com.fiap.domain.entity;

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
    @Column(name = "ID_ESTOQUE", columnDefinition = "NUMBER(8)")
    private Long id_estoque;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PRODUTO",
            columnDefinition = "NUMBER(8)",
            referencedColumnName = "ID_PRODUTO",
            foreignKey = @ForeignKey(name = "FK_PRODUTO_ESTOQUE"),
            nullable = false
    )
    private Produto id_produto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_VALOR_VARIACAO",
            columnDefinition = "NUMBER(8)",
            referencedColumnName = "ID_VALOR_VARIACAO",
            foreignKey = @ForeignKey(name = "FK_VALOR_VARIACAO_ESTOQUE"),
            nullable = false
    )
    private Valor_Variacao id_valor_variacao;

    @Column(name = "QTD_ESTOQUE", nullable = false, columnDefinition = "NUMBER(8)")
    private Long qtd_estoque;

    @Column(name = "PRECO_UNITARIO", nullable = false, columnDefinition = "NUMBER(10,2)")
    private BigDecimal preco_unitario;

    @Column(name = "IMG_URL_ESTOQUE", columnDefinition = "VARCHAR2(255)")
    private String img_url_estoque;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_USUARIO",
            columnDefinition = "NUMBER(8)",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_ESTOQUE"),
            nullable = false
    )
    private Usuario id_usuario;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BUYIT_PEDIDO_ESTOQUE",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_ESTOQUE",
                            columnDefinition = "NUMBER(8)",
                            referencedColumnName = "ID_ESTOQUE",
                            foreignKey = @ForeignKey(name = "FK_ESTOQUE_PEDIDO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_PEDIDO",
                            columnDefinition = "NUMBER(8)",
                            referencedColumnName = "ID_PEDIDO",
                            foreignKey = @ForeignKey(name = "FK_PEDIDO_ESTOQUE")
                    )
            }
    )
    private Set<Pedido> pedidos;

    public Estoque() {
    }

    public Estoque(Long id_estoque, Produto id_produto, Valor_Variacao id_valor_variacao, Long qtd_estoque, BigDecimal preco_unitario, String img_url_estoque, Usuario id_usuario, Set<Pedido> pedidos) {
        this.id_estoque = id_estoque;
        this.id_produto = id_produto;
        this.id_valor_variacao = id_valor_variacao;
        this.qtd_estoque = qtd_estoque;
        this.preco_unitario = preco_unitario;
        this.img_url_estoque = img_url_estoque;
        this.id_usuario = id_usuario;
        this.pedidos = Objects.nonNull(pedidos) ? pedidos : new LinkedHashSet<>();
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

    public Valor_Variacao getId_valor_variacao() {
        return id_valor_variacao;
    }

    public Estoque setId_valor_variacao(Valor_Variacao id_valor_variacao) {
        this.id_valor_variacao = id_valor_variacao;
        return this;
    }

    public Long getQtd_estoque() {
        return qtd_estoque;
    }

    public Estoque setQtd_estoque(Long qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
        return this;
    }

    public BigDecimal getPreco_unitario() {
        return preco_unitario;
    }

    public Estoque setPreco_unitario(BigDecimal preco_unitario) {
        this.preco_unitario = preco_unitario;
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
        return Objects.equals(id_estoque, estoque.id_estoque) && Objects.equals(id_produto, estoque.id_produto) && Objects.equals(id_valor_variacao, estoque.id_valor_variacao) && Objects.equals(qtd_estoque, estoque.qtd_estoque) && Objects.equals(preco_unitario, estoque.preco_unitario) && Objects.equals(img_url_estoque, estoque.img_url_estoque) && Objects.equals(id_usuario, estoque.id_usuario) && Objects.equals(pedidos, estoque.pedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_estoque, id_produto, id_valor_variacao, qtd_estoque, preco_unitario, img_url_estoque, id_usuario, pedidos);
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id_estoque=" + id_estoque +
                ", id_produto=" + id_produto +
                ", id_valor_variacao=" + id_valor_variacao +
                ", qtd_estoque=" + qtd_estoque +
                ", preco_unitario=" + preco_unitario +
                ", img_url_estoque='" + img_url_estoque + '\'' +
                ", id_usuario=" + id_usuario +
                '}';
    }
}