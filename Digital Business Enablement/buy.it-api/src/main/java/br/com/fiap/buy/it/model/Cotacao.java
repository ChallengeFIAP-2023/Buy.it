package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COTACAO")
public class Cotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COTACAO")
    @SequenceGenerator(name = "SQ_COTACAO", sequenceName = "SQ_COTACAO", allocationSize = 1)
    @Column(name = "ID_COTACAO")
    private Long id;

    @Column(name = "DATA_ABERTURA_COTACAO", nullable = false)
    @NotNull(message = "O campo data não pode estar vazio.")
    @PastOrPresent
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dataAbertura;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull(message = "O campo comprador não pode estar vazio.")
    @JoinColumn(
            name = "ID_USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_COMPRADOR_COTACAO"),
            nullable = false
    )
    private Usuario comprador;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull(message = "O campo produto não pode estar vazio.")
    @JoinColumn(
            name = "ID_PRODUTO",
            referencedColumnName = "ID_PRODUTO",
            foreignKey = @ForeignKey(name = "FK_PRODUTO_COTACAO"),
            nullable = false
    )
    private Produto produto;

    @Column(name = "QUANTIDADE_PRODUTO", nullable = false)
    @NotNull(message = "O campo quantidadeProduto não pode estar vazio.")
    @Positive
    private BigDecimal quantidadeProduto;

    @Column(name = "VALOR_PRODUTO", nullable = false)
    @NotNull(message = "O campo valorProduto não pode estar vazio.")
    @Positive
    private BigDecimal valorProduto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull(message = "O campo status não pode estar vazio.")
    @JoinColumn(
            name = "ID_STATUS",
            referencedColumnName = "ID_STATUS",
            foreignKey = @ForeignKey(name = "FK_STATUS_COTACAO"),
            nullable = false
    )
    private Status status;

    @Column(name = "PRIORIDADE_ENTREGA", nullable = false)
    @NotNull(message = "O campo prioridadeEntrega não pode estar vazio.")
    @Positive
    @Min(1) @Max(3)
    private Long prioridadeEntrega;

    @Column(name = "PRIORIDADE_QUALIDADE", nullable = false)
    @NotNull(message = "O campo prioridadeQualidade não pode estar vazio.")
    @Positive
    @Min(1) @Max(3)
    private Long prioridadeQualidade;

    @Column(name = "PRIORIDADE_PRECO", nullable = false)
    @NotNull(message = "O campo prioridadePreco não pode estar vazio.")
    @Positive
    @Min(1) @Max(3)
    private Long prioridadePreco;

    @Column(name = "PRAZO_COTACAO", nullable = false)
    @NotNull(message = "O campo prazo não pode estar vazio.")
    @PositiveOrZero
    private Long prazo;

    @Column(name = "DATA_FECHAMENTO_COTACAO")
    @PastOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dataFechamento;
}