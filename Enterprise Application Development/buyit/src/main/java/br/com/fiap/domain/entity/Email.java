package br.com.fiap.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BUYIT_EMAIL", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_ENDERECO_EMAIL", columnNames = "ENDERECO_EMAIL")
})
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_EMAIL")
    @SequenceGenerator(name = "BUYIT_SQ_EMAIL", sequenceName = "BUYIT_SQ_EMAIL", allocationSize = 1)
    @Column(name = "ID_EMAIL")
    private Long id;

    @Column(name = "ENDERECO_EMAIL", nullable = false)
    private String enderecoEmail;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_CONTATO",
            referencedColumnName = "ID_CONTATO",
            foreignKey = @ForeignKey(name = "FK_CONTATO_EMAIL"),
            nullable = false
    )
    private Contato contato;

    public Email() {
    }

    public Email(Long id, String enderecoEmail, Contato contato) {
        this.id = id;
        this.enderecoEmail = enderecoEmail;
        this.contato = contato;
    }

    public Long getId() {
        return id;
    }

    public Email setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEnderecoEmail() {
        return enderecoEmail;
    }

    public Email setEnderecoEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
        return this;
    }

    public Contato getContato() {
        return contato;
    }

    public Email setContato(Contato contato) {
        this.contato = contato;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email)) return false;

        if (!id.equals(email.id)) return false;
        if (!enderecoEmail.equals(email.enderecoEmail)) return false;
        return contato.equals(email.contato);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + enderecoEmail.hashCode();
        result = 31 * result + contato.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", enderecoEmail='" + enderecoEmail + '\'' +
                ", contato=" + contato +
                '}';
    }
}
