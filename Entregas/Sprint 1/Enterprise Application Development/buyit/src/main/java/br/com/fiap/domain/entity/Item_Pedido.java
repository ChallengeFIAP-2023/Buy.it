package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ITEM_PEDIDO")
public class Item_Pedido {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PEDIDO",
            referencedColumnName = "ID_PEDIDO",
            foreignKey = @ForeignKey(name = "FK_PEDIDO_ITEM_PEDIDO"),
            nullable = false
    )
    private Pedido id_pedido;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_VARIACAO",
            referencedColumnName = "ID_VARIACAO",
            foreignKey = @ForeignKey(name = "FK_VARIACAO_ITEM_PEDIDO"),
            nullable = false
    )
    private Variacao id_variacao;

    @Column(name = "QTD_ITEM_PEDIDO", nullable = false)
    private Long qtd_item_pedido;

    @Column(name = "VALOR_ITEM_PEDIDO", nullable = false)
    private BigDecimal valor_item_pedido;

    public Item_Pedido() {
    }

    public Item_Pedido(Pedido id_pedido, Variacao id_variacao, Long qtd_item_pedido, BigDecimal valor_item_pedido) {
        this.id_pedido = id_pedido;
        this.id_variacao = id_variacao;
        this.qtd_item_pedido = qtd_item_pedido;
        this.valor_item_pedido = valor_item_pedido;
    }

    public Pedido getId_pedido() {
        return id_pedido;
    }

    public Item_Pedido setId_pedido(Pedido id_pedido) {
        this.id_pedido = id_pedido;
        return this;
    }

    public Variacao getId_variacao() {
        return id_variacao;
    }

    public Item_Pedido setId_variacao(Variacao id_variacao) {
        this.id_variacao = id_variacao;
        return this;
    }

    public Long getQtd_item_pedido() {
        return qtd_item_pedido;
    }

    public Item_Pedido setQtd_item_pedido(Long qtd_item_pedido) {
        this.qtd_item_pedido = qtd_item_pedido;
        return this;
    }

    public BigDecimal getValor_item_pedido() {
        return valor_item_pedido;
    }

    public Item_Pedido setValor_item_pedido(BigDecimal valor_item_pedido) {
        this.valor_item_pedido = valor_item_pedido;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item_Pedido that)) return false;

        if (!id_pedido.equals(that.id_pedido)) return false;
        if (!id_variacao.equals(that.id_variacao)) return false;
        if (!qtd_item_pedido.equals(that.qtd_item_pedido)) return false;
        return valor_item_pedido.equals(that.valor_item_pedido);
    }

    @Override
    public int hashCode() {
        int result = id_pedido.hashCode();
        result = 31 * result + id_variacao.hashCode();
        result = 31 * result + qtd_item_pedido.hashCode();
        result = 31 * result + valor_item_pedido.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Item_Pedido{" +
                "id_pedido=" + id_pedido +
                ", id_variacao=" + id_variacao +
                ", qtd_item_pedido=" + qtd_item_pedido +
                ", valor_item_pedido=" + valor_item_pedido +
                '}';
    }
}
