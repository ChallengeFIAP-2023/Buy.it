package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "BUYIT_AVALIACAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_ID_COTACAO", columnNames = "ID_COTACAO")
})
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_AVALIACAO")
    @SequenceGenerator(name = "BUYIT_SQ_AVALIACAO", sequenceName = "BUYIT_SQ_AVALIACAO", allocationSize = 1)
    @Column(name = "ID_AVALIACAO")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ID_COTACAO", referencedColumnName = "ID_COTACAO", nullable = false)
    private Cotacao cotacao;

    @Column(name = "NOTA_PRECO_AVALIACAO", nullable = false)
    private Long notaPreco;

    @Column(name = "NOTA_QUALIDADE_AVALIACAO", nullable = false)
    private Long notaQualidade;

    @Column(name = "NOTA_ENTREGA_AVALIACAO", nullable = false)
    private Long notaEntrega;

    @Column(name = "DESCRICAO_AVALIACAO")
    private String descricao;

    @Column(name = "DATA_AVALIACAO", nullable = false)
    private LocalDate data;

    public Avaliacao() {
    }

    public Avaliacao(Long id, Cotacao cotacao, Long notaPreco, Long notaQualidade, Long notaEntrega, String descricao, LocalDate data) {
        this.id = id;
        this.cotacao = cotacao;
        this.notaPreco = notaPreco;
        this.notaQualidade = notaQualidade;
        this.notaEntrega = notaEntrega;
        this.descricao = descricao;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Avaliacao setId(Long id) {
        this.id = id;
        return this;
    }

    public Cotacao getCotacao() {
        return cotacao;
    }

    public Avaliacao setCotacao(Cotacao cotacao) {
        this.cotacao = cotacao;
        return this;
    }

    public Long getNotaPreco() {
        return notaPreco;
    }

    public Avaliacao setNotaPreco(Long notaPreco) {
        this.notaPreco = notaPreco;
        return this;
    }

    public Long getNotaQualidade() {
        return notaQualidade;
    }

    public Avaliacao setNotaQualidade(Long notaQualidade) {
        this.notaQualidade = notaQualidade;
        return this;
    }

    public Long getNotaEntrega() {
        return notaEntrega;
    }

    public Avaliacao setNotaEntrega(Long notaEntrega) {
        this.notaEntrega = notaEntrega;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Avaliacao setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public LocalDate getData() {
        return data;
    }

    public Avaliacao setData(LocalDate data) {
        this.data = data;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avaliacao avaliacao)) return false;

        if (!id.equals(avaliacao.id)) return false;
        if (!cotacao.equals(avaliacao.cotacao)) return false;
        if (!notaPreco.equals(avaliacao.notaPreco)) return false;
        if (!notaQualidade.equals(avaliacao.notaQualidade)) return false;
        if (!notaEntrega.equals(avaliacao.notaEntrega)) return false;
        if (!Objects.equals(descricao, avaliacao.descricao)) return false;
        return data.equals(avaliacao.data);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + cotacao.hashCode();
        result = 31 * result + notaPreco.hashCode();
        result = 31 * result + notaQualidade.hashCode();
        result = 31 * result + notaEntrega.hashCode();
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + data.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", cotacao=" + cotacao +
                ", notaPreco=" + notaPreco +
                ", notaQualidade=" + notaQualidade +
                ", notaEntrega=" + notaEntrega +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                '}';
    }
}
