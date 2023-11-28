package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "STATUS", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NOME_STATUS", columnNames = "NOME_STATUS")
})
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_STATUS")
    @SequenceGenerator(name = "SQ_STATUS", sequenceName = "SQ_STATUS", allocationSize = 1)
    @Column(name = "ID_STATUS")
    private Long id;

    @Column(name = "NOME_STATUS", nullable = false)
    @NotBlank(message = "O nome do status não pode estar vazio.")
    private String nome;
}