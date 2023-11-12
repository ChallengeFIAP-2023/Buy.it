package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "BUYIT_TIPO_VARIACAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_NM_TIPO_VARIACAO", columnNames = "NM_TIPO_VARIACAO")
})
public class TipoVariacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_VARIACAO")
    @SequenceGenerator(name = "SQ_TIPO_VARIACAO", sequenceName = "SQ_TIPO_VARIACAO", allocationSize = 1)
    @Column(name = "ID_TIPO_VARIACAO", columnDefinition = "NUMBER(8)")
    private Long id;

    @Column(name = "NM_TIPO_VARIACAO", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String nome;

    public TipoVariacao() {
    }

    public TipoVariacao(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public TipoVariacao setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public TipoVariacao setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoVariacao that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "TipoVariacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
