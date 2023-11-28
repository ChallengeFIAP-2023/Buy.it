package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "HISTORICO")
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HISTORICO")
    @SequenceGenerator(name = "SQ_HISTORICO", sequenceName = "SQ_HISTORICO", allocationSize = 1)
    @Column(name = "ID_HISTORICO")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_COTACAO",
            referencedColumnName = "ID_COTACAO",
            foreignKey = @ForeignKey(name = "FK_COTACAO_HISTORICO"),
            nullable = false
    )
    private Cotacao cotacao;

    @NotBlank(message = "O id do fornecedor não pode estar vazio.")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_FORNECEDOR",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_FORNECEDOR_HISTORICO"),
            nullable = false
    )
    private Usuario fornecedor;

    @NotBlank(message = "O id do status não pode estar vazio.")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_STATUS",
            referencedColumnName = "ID_STATUS",
            foreignKey = @ForeignKey(name = "FK_STATUS_HISTORICO"),
            nullable = false
    )
    private Status status;

    @Column(name = "RECUSADO_POR_PRODUTO", nullable = false)
    private Boolean recusadoPorProduto;

    @Column(name = "RECUSADO_POR_QUANTIDADE", nullable = false)
    private Boolean recusadoPorQuantidade;

    @Column(name = "RECUSADO_POR_PRECO", nullable = false)
    private Boolean recusadoPorPreco;

    @Column(name = "RECUSADO_POR_PRAZO", nullable = false)
    private Boolean recusadoPorPrazo;

    @Column(name = "DESCRICAO_HISTORICO", length = 400)
    private String descricao;

    @Column(name = "DATA_HISTORICO", nullable = false)
    @PastOrPresent
    private Date data;

    @Column(name = "VALOR_OFERTADO_HISTORICO")
    @Positive
    private BigDecimal valorOfertado;
}