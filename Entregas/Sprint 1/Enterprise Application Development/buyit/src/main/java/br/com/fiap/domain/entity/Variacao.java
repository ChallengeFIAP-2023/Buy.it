package br.com.fiap.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "VARIACAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_VARIACAO", columnNames = "NM_VARIACAO")
})
public class Variacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VARIACAO")
    @SequenceGenerator(name = "SQ_VARIACAO", sequenceName = "SQ_VARIACAO", allocationSize = 1)
    @Column(name = "ID_VARIACAO")
    private Long id_variacao;

    @Column(name = "NM_VARIACAO", nullable = false)
    private String nm_variacao;

    public Variacao() {
    }

    public Variacao(Long id_variacao, String nm_variacao) {
        this.id_variacao = id_variacao;
        this.nm_variacao = nm_variacao;
    }

    public Long getId_variacao() {
        return id_variacao;
    }

    public Variacao setId_variacao(Long id_variacao) {
        this.id_variacao = id_variacao;
        return this;
    }

    public String getNm_variacao() {
        return nm_variacao;
    }

    public Variacao setNm_variacao(String nm_variacao) {
        this.nm_variacao = nm_variacao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variacao variacao)) return false;

        if (!id_variacao.equals(variacao.id_variacao)) return false;
        return nm_variacao.equals(variacao.nm_variacao);
    }

    @Override
    public int hashCode() {
        int result = id_variacao.hashCode();
        result = 31 * result + nm_variacao.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Variacao{" +
                "id_variacao=" + id_variacao +
                ", nm_variacao='" + nm_variacao + '\'' +
                '}';
    }
}
