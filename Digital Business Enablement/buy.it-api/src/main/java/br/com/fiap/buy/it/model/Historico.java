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
@Table(name = "HISTORICO")
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HISTORICO")
    @SequenceGenerator(name = "SQ_HISTORICO", sequenceName = "SQ_HISTORICO", allocationSize = 1)
    @Column(name = "ID_HISTORICO")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull(message = "O campo cotacao não pode estar vazio.")
    @JoinColumn(
            name = "ID_COTACAO",
            referencedColumnName = "ID_COTACAO",
            foreignKey = @ForeignKey(name = "FK_COTACAO_HISTORICO"),
            nullable = false
    )
    private Cotacao cotacao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull(message = "O campo fornecedor não pode estar vazio.")
    @JoinColumn(
            name = "ID_FORNECEDOR",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_FORNECEDOR_HISTORICO"),
            nullable = false
    )
    private Usuario fornecedor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull(message = "O campo status não pode estar vazio.")
    @JoinColumn(
            name = "ID_STATUS",
            referencedColumnName = "ID_STATUS",
            foreignKey = @ForeignKey(name = "FK_STATUS_HISTORICO"),
            nullable = false
    )
    private Status status;

    @Column(name = "RECUSADO_POR_PRODUTO", nullable = false)
    @NotNull(message = "O campo recusadoPorProduto não pode estar vazio.")
    private Boolean recusadoPorProduto;

    @Column(name = "RECUSADO_POR_QUANTIDADE", nullable = false)
    @NotNull(message = "O campo recusadoPorQuantidade não pode estar vazio.")
    private Boolean recusadoPorQuantidade;

    @Column(name = "RECUSADO_POR_PRECO", nullable = false)
    @NotNull(message = "O campo recusadoPorPreco não pode estar vazio.")
    private Boolean recusadoPorPreco;

    @Column(name = "RECUSADO_POR_PRAZO", nullable = false)
    @NotNull(message = "O campo recusadoPorPrazo não pode estar vazio.")
    private Boolean recusadoPorPrazo;

    @Column(name = "DESCRICAO_HISTORICO", length = 400)
    private String descricao;

    @Column(name = "DATA_HISTORICO", nullable = false)
    @NotNull(message = "O campo data não pode estar vazio.")
    @PastOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date data;

    @Column(name = "VALOR_OFERTADO_HISTORICO")
    @Positive
    private BigDecimal valorOfertado;
}