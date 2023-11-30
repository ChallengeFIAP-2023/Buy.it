package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TIPO_CONTATO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_TIPO_CONTATO", columnNames = "NOME_TIPO_CONTATO")
})
public class TipoContato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_CONTATO")
    @SequenceGenerator(name = "SQ_TIPO_CONTATO", sequenceName = "SQ_TIPO_CONTATO", allocationSize = 1)
    @Column(name = "ID_TIPO_CONTATO")
    private Long id;

    @Column(name = "NOME_TIPO_CONTATO", nullable = false)
    @NotBlank(message = "O campo nome não pode estar vazio.")
    private String nome;
}