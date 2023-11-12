package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "BUYIT_DESCONTO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_ID_ESTOQUE", columnNames = "ID_ESTOQUE")
})
public class Desconto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DESCONTO")
    @SequenceGenerator(name = "SQ_DESCONTO", sequenceName = "SQ_DESCONTO", allocationSize = 1)
    @Column(name = "ID_DESCONTO")
    private Long id;

    @Column(name = "QTD_MIN_PRODUTO", nullable = false)
    private Long qtdMinProduto;

    @Column(name = "DESCONTO", nullable = false)
    private BigDecimal desconto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_ESTOQUE",
            referencedColumnName = "ID_ESTOQUE",
            foreignKey = @ForeignKey(name = "FK_ESTOQUE_DESCONTO"),
            nullable = false
    )
    private Estoque estoque;

    public Desconto() {
    }

    public Desconto(Long id, Long qtdMinProduto, BigDecimal desconto, Estoque estoque) {
        this.id = id;
        this.qtdMinProduto = qtdMinProduto;
        this.desconto = desconto;
        this.estoque = estoque;
    }

    public Long getId() {
        return id;
    }

    public Desconto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getQtdMinProduto() {
        return qtdMinProduto;
    }

    public Desconto setQtdMinProduto(Long qtdMinProduto) {
        this.qtdMinProduto = qtdMinProduto;
        return this;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public Desconto setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
        return this;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public Desconto setEstoque(Estoque estoque) {
        this.estoque = estoque;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Desconto desconto1)) return false;
        return Objects.equals(id, desconto1.id) && Objects.equals(qtdMinProduto, desconto1.qtdMinProduto) && Objects.equals(desconto, desconto1.desconto) && Objects.equals(estoque, desconto1.estoque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qtdMinProduto, desconto, estoque);
    }

    @Override
    public String toString() {
        return "Desconto{" +
                "id=" + id +
                ", qtdMinProduto=" + qtdMinProduto +
                ", desconto=" + desconto +
                ", estoque=" + estoque +
                '}';
    }
}
