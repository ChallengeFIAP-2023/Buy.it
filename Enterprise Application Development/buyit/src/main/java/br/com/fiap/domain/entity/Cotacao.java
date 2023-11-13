package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "BUYIT_USUARIO")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_COTACAO")
    @SequenceGenerator(name = "BUYIT_SQ_COTACAO", sequenceName = "BUYIT_SQ_COTACAO", allocationSize = 1)
    @Column(name = "ID_COTACAO")
    private Long id;

    @Column(name = "DATA_ABERTURA_COTACAO", nullable = false)
    private LocalDate abertura;

    @Column(name = "DATA_FECHAMENTO_COTACAO")
    private LocalDate fechamento;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_COMPRADOR",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_COMPRADOR_COTACAO"),
            nullable = false
    )
    private Usuario comprador;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_FORNECEDOR",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_FORNECEDOR_COTACAO")
    )
    private Usuario fornecedor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PRODUTO",
            referencedColumnName = "ID_PRODUTO",
            foreignKey = @ForeignKey(name = "FK_PRODUTO_COTACAO"),
            nullable = false
    )
    private Produto produto;

    @Column(name = "QUANTIDADE_PRODUTO", nullable = false)
    private BigDecimal quantidade;

    @Column(name = "VALOR_COTACAO", nullable = false)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_STATUS",
            referencedColumnName = "ID_STATUS",
            foreignKey = @ForeignKey(name = "FK_STATUS_COTACAO"),
            nullable = false
    )
    private Status status;

    @Column(name = "PRIORIDADE_ENTREGA", nullable = false)
    private Long entrega;

    @Column(name = "PRIORIDADE_QUALIDADE", nullable = false)
    private Long qualidade;

    @Column(name = "PRIORIDADE_PRECO", nullable = false)
    private Long preco;

    @Column(name = "PRAZO_COTACAO", nullable = false)
    private Long prazo;

    public Cotacao() {
    }

    public Cotacao(Long id, LocalDate abertura, LocalDate fechamento, Usuario comprador, Usuario fornecedor, Produto produto, BigDecimal quantidade, BigDecimal valor, Status status, Long entrega, Long qualidade, Long preco, Long prazo) {
        this.id = id;
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.comprador = comprador;
        this.fornecedor = fornecedor;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.status = status;
        this.entrega = entrega;
        this.qualidade = qualidade;
        this.preco = preco;
        this.prazo = prazo;
    }

    public Long getId() {
        return id;
    }

    public Cotacao setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getAbertura() {
        return abertura;
    }

    public Cotacao setAbertura(LocalDate abertura) {
        this.abertura = abertura;
        return this;
    }

    public LocalDate getFechamento() {
        return fechamento;
    }

    public Cotacao setFechamento(LocalDate fechamento) {
        this.fechamento = fechamento;
        return this;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Cotacao setComprador(Usuario comprador) {
        this.comprador = comprador;
        return this;
    }

    public Usuario getFornecedor() {
        return fornecedor;
    }

    public Cotacao setFornecedor(Usuario fornecedor) {
        this.fornecedor = fornecedor;
        return this;
    }

    public Produto getProduto() {
        return produto;
    }

    public Cotacao setProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public Cotacao setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Cotacao setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Cotacao setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Long getEntrega() {
        return entrega;
    }

    public Cotacao setEntrega(Long entrega) {
        this.entrega = entrega;
        return this;
    }

    public Long getQualidade() {
        return qualidade;
    }

    public Cotacao setQualidade(Long qualidade) {
        this.qualidade = qualidade;
        return this;
    }

    public Long getPreco() {
        return preco;
    }

    public Cotacao setPreco(Long preco) {
        this.preco = preco;
        return this;
    }

    public Long getPrazo() {
        return prazo;
    }

    public Cotacao setPrazo(Long prazo) {
        this.prazo = prazo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cotacao cotacao)) return false;

        if (!id.equals(cotacao.id)) return false;
        if (!abertura.equals(cotacao.abertura)) return false;
        if (!Objects.equals(fechamento, cotacao.fechamento)) return false;
        if (!comprador.equals(cotacao.comprador)) return false;
        if (!Objects.equals(fornecedor, cotacao.fornecedor)) return false;
        if (!produto.equals(cotacao.produto)) return false;
        if (!quantidade.equals(cotacao.quantidade)) return false;
        if (!valor.equals(cotacao.valor)) return false;
        if (!status.equals(cotacao.status)) return false;
        if (!entrega.equals(cotacao.entrega)) return false;
        if (!qualidade.equals(cotacao.qualidade)) return false;
        if (!preco.equals(cotacao.preco)) return false;
        return prazo.equals(cotacao.prazo);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + abertura.hashCode();
        result = 31 * result + (fechamento != null ? fechamento.hashCode() : 0);
        result = 31 * result + comprador.hashCode();
        result = 31 * result + (fornecedor != null ? fornecedor.hashCode() : 0);
        result = 31 * result + produto.hashCode();
        result = 31 * result + quantidade.hashCode();
        result = 31 * result + valor.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + entrega.hashCode();
        result = 31 * result + qualidade.hashCode();
        result = 31 * result + preco.hashCode();
        result = 31 * result + prazo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cotacao{" +
                "id=" + id +
                ", abertura=" + abertura +
                ", fechamento=" + fechamento +
                ", comprador=" + comprador +
                ", fornecedor=" + fornecedor +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", status=" + status +
                ", entrega=" + entrega +
                ", qualidade=" + qualidade +
                ", preco=" + preco +
                ", prazo=" + prazo +
                '}';
    }
}
