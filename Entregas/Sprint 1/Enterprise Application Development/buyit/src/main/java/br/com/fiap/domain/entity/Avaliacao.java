package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "BUYIT_AVALIACAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_ID_PEDIDO", columnNames = "ID_PEDIDO")
})
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AVALIACAO")
    @SequenceGenerator(name = "SQ_AVALIACAO", sequenceName = "SQ_AVALIACAO", allocationSize = 1)
    @Column(name = "ID_AVALIACAO")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_AVALIACAO"),
            nullable = false
    )
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PEDIDO",
            referencedColumnName = "ID_PEDIDO",
            foreignKey = @ForeignKey(name = "FK_PEDIDO_AVALIACAO"),
            nullable = false
    )
    private Pedido pedido;

    @Column(name = "NOTA_PRECO_AVALIACAO")
    private Long notaPreco;

    @Column(name = "NOTA_QUALIDADE_AVALIACAO")
    private Long notaQualidade;

    @Column(name = "NOTA_ENTREGA_AVALIACAO")
    private Long notaEntrega;

    @Column(name = "DS_AVALIACAO")
    private String descricao;

    public Avaliacao() {
    }

    public Avaliacao(Long id, Usuario usuario, Pedido pedido, Long notaPreco, Long notaQualidade, Long notaEntrega, String descricao) {
        this.id = id;
        this.usuario = usuario;
        this.pedido = pedido;
        this.notaPreco = notaPreco;
        this.notaQualidade = notaQualidade;
        this.notaEntrega = notaEntrega;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public Avaliacao setId(Long id) {
        this.id = id;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Avaliacao setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Avaliacao setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avaliacao avaliacao)) return false;
        return Objects.equals(id, avaliacao.id) && Objects.equals(usuario, avaliacao.usuario) && Objects.equals(pedido, avaliacao.pedido) && Objects.equals(notaPreco, avaliacao.notaPreco) && Objects.equals(notaQualidade, avaliacao.notaQualidade) && Objects.equals(notaEntrega, avaliacao.notaEntrega) && Objects.equals(descricao, avaliacao.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, pedido, notaPreco, notaQualidade, notaEntrega, descricao);
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", pedido=" + pedido +
                ", notaPreco=" + notaPreco +
                ", notaQualidade=" + notaQualidade +
                ", notaEntrega=" + notaEntrega +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
