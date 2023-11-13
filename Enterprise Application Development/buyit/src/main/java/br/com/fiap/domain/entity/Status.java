package br.com.fiap.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BUYIT_STATUS", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_NOME_STATUS", columnNames = "NOME_STATUS")
})
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_STATUS")
    @SequenceGenerator(name = "BUYIT_SQ_STATUS", sequenceName = "BUYIT_SQ_STATUS", allocationSize = 1)
    @Column(name = "ID_STATUS")
    private Long id;

    @Column(name = "NOME_STATUS", nullable = false)
    private String nome;

    public Status() {
    }

    public Status(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public Status setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Status setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Status status)) return false;

        if (!id.equals(status.id)) return false;
        return nome.equals(status.nome);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nome.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
