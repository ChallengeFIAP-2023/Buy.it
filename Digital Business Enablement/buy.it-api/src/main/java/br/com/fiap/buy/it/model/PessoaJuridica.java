package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "PESSOA_JURIDICA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CNPJ_PJ", columnNames = "CNPJ_PJ")
})
@DiscriminatorValue("PJ")
public class PessoaJuridica extends Pessoa {
    @Column(name = "CNPJ_PJ", nullable = false)
    @NotBlank(message = "O campo cnpj não pode estar vazio.")
    private String cnpj;

    @Column(name = "IS_FORNECEDOR_PJ", nullable = false)
    @NotNull(message = "O campo isFornecedor não pode estar vazio.")
    private Boolean isFornecedor;
}