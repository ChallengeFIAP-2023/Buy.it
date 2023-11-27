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
@Table(name = "PESSOA_JURIDICA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CNPJ_PJ", columnNames = "CNPJ_PJ")
})
@DiscriminatorValue("PJ")
public class PessoaJuridica extends Pessoa {
    @Column(name = "CNPJ_PJ", nullable = false)
    @NotBlank (message = "O cnpj da pessoa juridica não pode estar vazio.")
    private String cnpj;

    @Column(name = "IS_FORNECEDOR_PJ", nullable = false)
    @NotBlank(message = "O isFornecedor da pessoa juridica não pode estar vazio." )
    private Boolean isFornecedor;
}