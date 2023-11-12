package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "BUYIT_VALOR_VARIACAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_NM_VALOR_VARIACAO", columnNames = "NM_VALOR_VARIACAO")
})
public class ValorVariacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VALOR_VARIACAO")
    @SequenceGenerator(name = "SQ_VALOR_VARIACAO", sequenceName = "SQ_VALOR_VARIACAO", allocationSize = 1)
    @Column(name = "ID_VALOR_VARIACAO", columnDefinition = "NUMBER(8)")
    private Long id;

    @Column(name = "NM_VALOR_VARIACAO", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_TIPO_VARIACAO",
            columnDefinition = "NUMBER(8)",
            referencedColumnName = "ID_TIPO_VARIACAO",
            foreignKey = @ForeignKey(name = "FK_TIPO_VARIACAO_VALOR_VARIACAO"),
            nullable = false
    )
    private TipoVariacao tipoVariacao;

    public ValorVariacao() {
    }

    public ValorVariacao(Long id, String nome, TipoVariacao tipoVariacao) {
        this.id = id;
        this.nome = nome;
        this.tipoVariacao = tipoVariacao;
    }

    public Long getId() {
        return id;
    }

    public ValorVariacao setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public ValorVariacao setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public TipoVariacao getTipoVariacao() {
        return tipoVariacao;
    }

    public ValorVariacao setTipoVariacao(TipoVariacao tipoVariacao) {
        this.tipoVariacao = tipoVariacao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValorVariacao that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(tipoVariacao, that.tipoVariacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tipoVariacao);
    }

    @Override
    public String toString() {
        return "ValorVariacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoVariacao=" + tipoVariacao +
                '}';
    }
}
