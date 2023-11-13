package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "BUYIT_HISTORICO")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_HISTORICO")
    @SequenceGenerator(name = "BUYIT_SQ_HISTORICO", sequenceName = "BUYIT_SQ_HISTORICO", allocationSize = 1)
    @Column(name = "ID_HISTORICO")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_COTACAO",
            referencedColumnName = "ID_COTACAO",
            foreignKey = @ForeignKey(name = "FK_COTACAO_HISTORICO"),
            nullable = false
    )
    private Cotacao cotacao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_STATUS",
            referencedColumnName = "ID_STATUS",
            foreignKey = @ForeignKey(name = "FK_STATUS_HISTORICO"),
            nullable = false
    )
    private Status status;

    @Column(name = "DATA_HISTORICO", nullable = false)
    private LocalDate data;

    @Column(name = "RECUSADO_POR_PRODUTO", nullable = false)
    private Boolean recusadoProduto;

    @Column(name = "RECUSADO_POR_QUANTIDADE", nullable = false)
    private Boolean recusadoQuantidade;

    @Column(name = "RECUSADO_POR_PRECO", nullable = false)
    private Boolean recusadoPreco;

    @Column(name = "RECUSADO_POR_PRAZO", nullable = false)
    private Boolean recusadoPrazo;

    @Column(name = "DESCRICAO_HISTORICO")
    private String descricao;

    public Historico() {
    }

    public Historico(Long id, Cotacao cotacao, Status status, LocalDate data, Boolean recusadoProduto, Boolean recusadoQuantidade, Boolean recusadoPreco, Boolean recusadoPrazo, String descricao) {
        this.id = id;
        this.cotacao = cotacao;
        this.status = status;
        this.data = data;
        this.recusadoProduto = recusadoProduto;
        this.recusadoQuantidade = recusadoQuantidade;
        this.recusadoPreco = recusadoPreco;
        this.recusadoPrazo = recusadoPrazo;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public Historico setId(Long id) {
        this.id = id;
        return this;
    }

    public Cotacao getCotacao() {
        return cotacao;
    }

    public Historico setCotacao(Cotacao cotacao) {
        this.cotacao = cotacao;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Historico setStatus(Status status) {
        this.status = status;
        return this;
    }

    public LocalDate getData() {
        return data;
    }

    public Historico setData(LocalDate data) {
        this.data = data;
        return this;
    }

    public Boolean getRecusadoProduto() {
        return recusadoProduto;
    }

    public Historico setRecusadoProduto(Boolean recusadoProduto) {
        this.recusadoProduto = recusadoProduto;
        return this;
    }

    public Boolean getRecusadoQuantidade() {
        return recusadoQuantidade;
    }

    public Historico setRecusadoQuantidade(Boolean recusadoQuantidade) {
        this.recusadoQuantidade = recusadoQuantidade;
        return this;
    }

    public Boolean getRecusadoPreco() {
        return recusadoPreco;
    }

    public Historico setRecusadoPreco(Boolean recusadoPreco) {
        this.recusadoPreco = recusadoPreco;
        return this;
    }

    public Boolean getRecusadoPrazo() {
        return recusadoPrazo;
    }

    public Historico setRecusadoPrazo(Boolean recusadoPrazo) {
        this.recusadoPrazo = recusadoPrazo;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Historico setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Historico historico)) return false;

        if (!id.equals(historico.id)) return false;
        if (!cotacao.equals(historico.cotacao)) return false;
        if (!status.equals(historico.status)) return false;
        if (!data.equals(historico.data)) return false;
        if (!recusadoProduto.equals(historico.recusadoProduto)) return false;
        if (!recusadoQuantidade.equals(historico.recusadoQuantidade)) return false;
        if (!recusadoPreco.equals(historico.recusadoPreco)) return false;
        if (!recusadoPrazo.equals(historico.recusadoPrazo)) return false;
        return Objects.equals(descricao, historico.descricao);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + cotacao.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + data.hashCode();
        result = 31 * result + recusadoProduto.hashCode();
        result = 31 * result + recusadoQuantidade.hashCode();
        result = 31 * result + recusadoPreco.hashCode();
        result = 31 * result + recusadoPrazo.hashCode();
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Historico{" +
                "id=" + id +
                ", cotacao=" + cotacao +
                ", status=" + status +
                ", data=" + data +
                ", recusadoProduto=" + recusadoProduto +
                ", recusadoQuantidade=" + recusadoQuantidade +
                ", recusadoPreco=" + recusadoPreco +
                ", recusadoPrazo=" + recusadoPrazo +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
