package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "BUYIT_TIPO_VARIACAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_NM_TIPO_VARIACAO", columnNames = "NM_TIPO_VARIACAO")
})
public class Tipo_Variacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_VARIACAO")
    @SequenceGenerator(name = "SQ_TIPO_VARIACAO", sequenceName = "SQ_TIPO_VARIACAO", allocationSize = 1)
    @Column(name = "ID_TIPO_VARIACAO", columnDefinition = "NUMBER(8)")
    private Long id_tipo_variacao;

    @Column(name = "NM_TIPO_VARIACAO", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String nm_tipo_variacao;

    public Tipo_Variacao() {
    }

    public Tipo_Variacao(Long id_tipo_variacao, String nm_tipo_variacao) {
        this.id_tipo_variacao = id_tipo_variacao;
        this.nm_tipo_variacao = nm_tipo_variacao;
    }

    public Long getId_tipo_variacao() {
        return id_tipo_variacao;
    }

    public Tipo_Variacao setId_tipo_variacao(Long id_tipo_variacao) {
        this.id_tipo_variacao = id_tipo_variacao;
        return this;
    }

    public String getNm_tipo_variacao() {
        return nm_tipo_variacao;
    }

    public Tipo_Variacao setNm_tipo_variacao(String nm_tipo_variacao) {
        this.nm_tipo_variacao = nm_tipo_variacao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tipo_Variacao that)) return false;
        return Objects.equals(id_tipo_variacao, that.id_tipo_variacao) && Objects.equals(nm_tipo_variacao, that.nm_tipo_variacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_tipo_variacao, nm_tipo_variacao);
    }

    @Override
    public String toString() {
        return "Tipo_Variacao{" +
                "id_tipo_variacao=" + id_tipo_variacao +
                ", nm_tipo_variacao='" + nm_tipo_variacao + '\'' +
                '}';
    }
}
