package br.com.fiap.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BUYIT_PESSOA_JURIDICA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_CNPJ_PJ", columnNames = "CNPJ_PJ")
})
@DiscriminatorValue("PJ")
public class PessoaJuridica extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_PJ")
    @SequenceGenerator(name = "BUYIT_SQ_PJ", sequenceName = "BUYIT_SQ_PJ", allocationSize = 1)
    @Column(name = "ID_PJ")
    private Long id;

    @Column(name = "CNPJ_PJ", nullable = false)
    private String cnpj;

    @Column(name = "IS_FORNECEDOR_PJ", nullable = false)
    private Boolean isFornecedor;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long id, String cnpj, Boolean isFornecedor) {
        this.id = id;
        this.cnpj = cnpj;
        this.isFornecedor = isFornecedor;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public PessoaJuridica setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public PessoaJuridica setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public Boolean getFornecedor() {
        return isFornecedor;
    }

    public PessoaJuridica setFornecedor(Boolean fornecedor) {
        isFornecedor = fornecedor;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaJuridica that)) return false;
        if (!super.equals(o)) return false;

        if (!id.equals(that.id)) return false;
        if (!cnpj.equals(that.cnpj)) return false;
        return isFornecedor.equals(that.isFornecedor);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + cnpj.hashCode();
        result = 31 * result + isFornecedor.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "id=" + id +
                ", cnpj='" + cnpj + '\'' +
                ", isFornecedor=" + isFornecedor +
                "} " + super.toString();
    }
}
