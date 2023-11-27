package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "COTACAO")
public class Cotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COTACAO")
    @SequenceGenerator(name = "SQ_COTACAO", sequenceName = "SQ_COTACAO", allocationSize = 1)
    @Column(name = "ID_COTACAO")
    private Long id;

    @Column(name = "DATA_ABERTURA_COTACAO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAbertura;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_COMPRADOR",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_COMPRADOR_COTACAO"),
            nullable = false
    )
    private Usuario comprador;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PRODUTO",
            referencedColumnName = "ID_PRODUTO",
            foreignKey = @ForeignKey(name = "FK_PRODUTO_COTACAO"),
            nullable = false
    )
    private Produto produto;

    @Column(name = "QUANTIDADE_PRODUTO", nullable = false)
    @Positive
    private BigDecimal quantidadeProduto;

    @Column(name = "VALOR_PRODUTO", nullable = false)
    @Positive
    private BigDecimal valorProduto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_STATUS",
            referencedColumnName = "ID_STATUS",
            foreignKey = @ForeignKey(name = "FK_STATUS_COTACAO"),
            nullable = false
    )
    private Status status;

    @Column(name = "PRIORIDADE_ENTREGA", nullable = false)
    @Min(1) @Max(3)
    private Integer prioridadeEntrega;

    @Column(name = "PRIORIDADE_QUALIDADE", nullable = false)
    @Min(1) @Max(3)
    private Integer prioridadeQualidade;

    @Column(name = "PRIORIDADE_PRECO", nullable = false)
    @Min(1) @Max(3)
    private Integer prioridadePreco;

    @Column(name = "PRAZO_COTACAO", nullable = false)
    @PositiveOrZero
    private Integer prazo;

    @Column(name = "DATA_FECHAMENTO_COTACAO")
    @PastOrPresent
    private Date dataFechamento;
}