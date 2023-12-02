package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTATO")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CONTATO")
    @SequenceGenerator(name = "SQ_CONTATO", sequenceName = "SQ_CONTATO", allocationSize = 1)
    @Column(name = "ID_CONTATO")
    private Long id;

    @Column(name = "TIPO_CONTATO", nullable = false)
    @NotNull(message = "O campo tipo não pode estar vazio.")
    private String tipo;

    @Column(name = "VALOR_CONTATO", nullable = false)
    @NotBlank(message = "O campo valor não pode estar vazio.")
    private String valor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull(message = "O campo usuário não pode estar vazio.")
    @JoinColumn(
            name = "ID_USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_CONTATO"),
            nullable = false
    )
    private Usuario usuario;
}