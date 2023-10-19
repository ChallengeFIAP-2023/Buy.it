package br.com.fiap.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "OPCAO_VARIACAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_OPCAO_VARIACAO", columnNames = "NM_OPCAO_VARIACAO")
})
public class Opcao_Variacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OPCAO_VARIACAO")
    @SequenceGenerator(name = "SQ_OPCAO_VARIACAO", sequenceName = "SQ_OPCAO_VARIACAO", allocationSize = 1)
    @Column(name = "ID_OPCAO_VARIACAO")
    private Long id_opcao_variacao;

    @Column(name = "NM_OPCAO_VARIACAO", nullable = false)
    private String nm_opcao_variacao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_VARIACAO",
            referencedColumnName = "ID_VARIACAO",
            foreignKey = @ForeignKey(name = "FK_VARIACAO_OPCAO_VARIACAO"),
            nullable = false
    )
    private Variacao id_variacao;

    public Opcao_Variacao() {
    }

    public Opcao_Variacao(Long id_opcao_variacao, String nm_opcao_variacao, Variacao id_variacao) {
        this.id_opcao_variacao = id_opcao_variacao;
        this.nm_opcao_variacao = nm_opcao_variacao;
        this.id_variacao = id_variacao;
    }

    public Long getId_opcao_variacao() {
        return id_opcao_variacao;
    }

    public Opcao_Variacao setId_opcao_variacao(Long id_opcao_variacao) {
        this.id_opcao_variacao = id_opcao_variacao;
        return this;
    }

    public String getNm_opcao_variacao() {
        return nm_opcao_variacao;
    }

    public Opcao_Variacao setNm_opcao_variacao(String nm_opcao_variacao) {
        this.nm_opcao_variacao = nm_opcao_variacao;
        return this;
    }

    public Variacao getId_variacao() {
        return id_variacao;
    }

    public Opcao_Variacao setId_variacao(Variacao id_variacao) {
        this.id_variacao = id_variacao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Opcao_Variacao that)) return false;

        if (!id_opcao_variacao.equals(that.id_opcao_variacao)) return false;
        if (!nm_opcao_variacao.equals(that.nm_opcao_variacao)) return false;
        return id_variacao.equals(that.id_variacao);
    }

    @Override
    public int hashCode() {
        int result = id_opcao_variacao.hashCode();
        result = 31 * result + nm_opcao_variacao.hashCode();
        result = 31 * result + id_variacao.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Opcao_Variacao{" +
                "id_opcao_variacao=" + id_opcao_variacao +
                ", nm_opcao_variacao='" + nm_opcao_variacao + '\'' +
                ", id_variacao=" + id_variacao +
                '}';
    }
}
