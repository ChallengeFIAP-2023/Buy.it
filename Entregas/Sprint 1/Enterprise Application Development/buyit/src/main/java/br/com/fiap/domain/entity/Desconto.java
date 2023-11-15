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
    @Column(name = "ID_DESCONTO", columnDefinition = "NUMBER(8)")
    private Long id_desconto;

    @Column(name = "QTD_MIN_PRODUTO", nullable = false, columnDefinition = "NUMBER(8)")
    private Long qtd_min_produto;

    @Column(name = "DESCONTO", nullable = false, columnDefinition = "NUMBER(5,2)")
    private BigDecimal desconto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_ESTOQUE",
            columnDefinition = "NUMBER(8)",
            referencedColumnName = "ID_ESTOQUE",
            foreignKey = @ForeignKey(name = "FK_ESTOQUE_DESCONTO"),
            nullable = false
    )
    private Estoque id_estoque;

    public Desconto() {
    }

    public Desconto(Long id_desconto, Long qtd_min_produto, BigDecimal desconto, Estoque id_estoque) {
        this.id_desconto = id_desconto;
        this.qtd_min_produto = qtd_min_produto;
        this.desconto = desconto;
        this.id_estoque = id_estoque;
    }

    public Long getId_desconto() {
        return id_desconto;
    }

    public Desconto setId_desconto(Long id_desconto) {
        this.id_desconto = id_desconto;
        return this;
    }

    public Long getQtd_min_produto() {
        return qtd_min_produto;
    }

    public Desconto setQtd_min_produto(Long qtd_min_produto) {
        this.qtd_min_produto = qtd_min_produto;
        return this;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public Desconto setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
        return this;
    }

    public Estoque getId_estoque() {
        return id_estoque;
    }

    public Desconto setId_estoque(Estoque id_estoque) {
        this.id_estoque = id_estoque;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Desconto desconto1)) return false;
        return Objects.equals(id_desconto, desconto1.id_desconto) && Objects.equals(qtd_min_produto, desconto1.qtd_min_produto) && Objects.equals(desconto, desconto1.desconto) && Objects.equals(id_estoque, desconto1.id_estoque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_desconto, qtd_min_produto, desconto, id_estoque);
    }

    @Override
    public String toString() {
        return "Desconto{" +
                "id_desconto=" + id_desconto +
                ", qtd_min_produto=" + qtd_min_produto +
                ", desconto=" + desconto +
                ", id_estoque=" + id_estoque +
                '}';
    }
}
