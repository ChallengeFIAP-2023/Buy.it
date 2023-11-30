package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FORMA_CONTATO")
public class FormaContato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FORMA_CONTATO")
    @SequenceGenerator(name = "SQ_FORMA_CONTATO", sequenceName = "SQ_FORMA_CONTATO", allocationSize = 1)
    @Column(name = "ID_FORMA_CONTATO")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull(message = "O campo tipoContato não pode estar vazio.")
    @JoinColumn(
            name = "ID_TIPO_CONTATO",
            referencedColumnName = "ID_TIPO_CONTATO",
            foreignKey = @ForeignKey(name = "FK_TIPO_CONTATO_FORMA_CONTATO"),
            nullable = false
    )
    private TipoContato tipoContato;

    @Column(name = "VALOR_FORMA_CONTATO", nullable = false)
    @NotBlank(message = "O campo valor não pode estar vazio.")
    private String valor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull(message = "O campo pessoa não pode estar vazio.")
    @JoinColumn(
            name = "ID_PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_PESSOA_FORMA_CONTATO"),
            nullable = false
    )
    private Pessoa pessoa;
}