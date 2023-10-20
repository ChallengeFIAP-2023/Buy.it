package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "VALOR_VARIACAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_VALOR_VARIACAO", columnNames = "NM_VALOR_VARIACAO")
})
public class Valor_Variacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VALOR_VARIACAO")
    @SequenceGenerator(name = "SQ_VALOR_VARIACAO", sequenceName = "SQ_VALOR_VARIACAO", allocationSize = 1)
    @Column(name = "ID_VALOR_VARIACAO")
    private Long id_valor_variacao;

    @Column(name = "NM_VALOR_VARIACAO", nullable = false)
    private String nm_valor_variacao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_TIPO_VARIACAO",
            referencedColumnName = "ID_TIPO_VARIACAO",
            foreignKey = @ForeignKey(name = "FK_TIPO_VARIACAO_VALOR_VARIACAO"),
            nullable = false
    )
    private Tipo_Variacao id_tipo_variacao;

    public Valor_Variacao() {
    }

    public Valor_Variacao(Long id_valor_variacao, String nm_valor_variacao, Tipo_Variacao id_tipo_variacao) {
        this.id_valor_variacao = id_valor_variacao;
        this.nm_valor_variacao = nm_valor_variacao;
        this.id_tipo_variacao = id_tipo_variacao;
    }

    public Long getId_valor_variacao() {
        return id_valor_variacao;
    }

    public Valor_Variacao setId_valor_variacao(Long id_valor_variacao) {
        this.id_valor_variacao = id_valor_variacao;
        return this;
    }

    public String getNm_valor_variacao() {
        return nm_valor_variacao;
    }

    public Valor_Variacao setNm_valor_variacao(String nm_valor_variacao) {
        this.nm_valor_variacao = nm_valor_variacao;
        return this;
    }

    public Tipo_Variacao getId_tipo_variacao() {
        return id_tipo_variacao;
    }

    public Valor_Variacao setId_tipo_variacao(Tipo_Variacao id_tipo_variacao) {
        this.id_tipo_variacao = id_tipo_variacao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Valor_Variacao that)) return false;
        return Objects.equals(id_valor_variacao, that.id_valor_variacao) && Objects.equals(nm_valor_variacao, that.nm_valor_variacao) && Objects.equals(id_tipo_variacao, that.id_tipo_variacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_valor_variacao, nm_valor_variacao, id_tipo_variacao);
    }

    @Override
    public String toString() {
        return "Valor_Variacao{" +
                "id_valor_variacao=" + id_valor_variacao +
                ", nm_valor_variacao='" + nm_valor_variacao + '\'' +
                ", id_tipo_variacao=" + id_tipo_variacao +
                '}';
    }
}
