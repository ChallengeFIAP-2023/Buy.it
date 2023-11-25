package br.com.fiap.buy.it.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @JoinColumn(
            name = "ID_COTACAO",
            referencedColumnName = "ID_COTACAO",
            foreignKey = @ForeignKey(name = "FK_COTACAO_AVALIACAO"),
            nullable = false)
    private Cotacao cotacao;

    @Column(name = "DATA_AVALIACAO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "NOTA_ENTREGA_AVALIACAO", nullable = false)
    @Min(1) @Max(5)
    private Integer notaEntrega;

    @Column(name = "NOTA_QUALIDADE_AVALIACAO", nullable = false)
    @Min(1) @Max(5)
    private Integer notaQualidade;

    @Column(name = "NOTA_PRECO_AVALIACAO", nullable = false)
    @Min(1) @Max(5)
    private Integer notaPreco;

    @Column(name = "DESCRICAO_AVALIACAO", length = 400)
    private String descricao;


}


