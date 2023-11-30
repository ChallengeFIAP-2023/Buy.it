package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AVALIACAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_ID_COTACAO", columnNames = "ID_COTACAO")
})
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AVALIACAO")
    @SequenceGenerator(name = "SQ_AVALIACAO", sequenceName = "SQ_AVALIACAO", allocationSize = 1)
    @Column(name = "ID_AVALIACAO")
    private Long id;

    @OneToOne
    @NotNull(message = "O campo cotacao não pode estar vazio.")
    @JoinColumn(
            name = "ID_COTACAO",
            referencedColumnName = "ID_COTACAO",
            foreignKey = @ForeignKey(name = "FK_COTACAO_AVALIACAO"),
            nullable = false)
    private Cotacao cotacao;

    @Column(name = "DATA_AVALIACAO", nullable = false)
    @NotNull(message = "O campo data não pode estar vazio.")
    @PastOrPresent
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "NOTA_ENTREGA_AVALIACAO", nullable = false)
    @NotNull(message = "O campo notaEntrega não pode estar vazio.")
    @Positive
    @Min(1) @Max(5)
    private Long notaEntrega;

    @Column(name = "NOTA_QUALIDADE_AVALIACAO", nullable = false)
    @NotNull(message = "O campo notaQualidade não pode estar vazio.")
    @Positive
    @Min(1) @Max(5)
    private Long notaQualidade;

    @Column(name = "NOTA_PRECO_AVALIACAO", nullable = false)
    @NotNull(message = "O campo notaPreco não pode estar vazio.")
    @Positive
    @Min(1) @Max(5)
    private Long notaPreco;

    @Column(name = "DESCRICAO_AVALIACAO", length = 400)
    private String descricao;
}