package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "FORMA_CONTATO")
public class FormaContato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FORMA_CONTATO")
    @SequenceGenerator(name = "SQ_FORMA_CONTATO", sequenceName = "SQ_FORMA_CONTATO", allocationSize = 1)
    @Column(name = "ID_FORMA_CONTATO")
    private Long id;

    //@NotBlank(message = "O id tipo contato é obrigatório.")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_TIPO_CONTATO",
            referencedColumnName = "ID_TIPO_CONTATO",
            foreignKey = @ForeignKey(name = "FK_TIPO_CONTATO_FORMA_CONTATO"),
            nullable = false
    )
    private TipoContato tipoContato;

    @NotBlank(message = "O valor da forma de contato é obrigatório.")
    @Column(name = "VALOR_FORMA_CONTATO", nullable = false)
    private String valor;

    //@NotBlank(message = "O id de pessoa é obrigatório.")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_PESSOA_FORMA_CONTATO"),
            nullable = false
    )
    private Pessoa pessoa;
}