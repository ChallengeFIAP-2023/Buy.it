package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "AVALIACAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_ID_PEDIDO", columnNames = "ID_PEDIDO")
})
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AVALIACAO")
    @SequenceGenerator(name = "SQ_AVALIACAO", sequenceName = "SQ_AVALIACAO", allocationSize = 1)
    @Column(name = "ID_AVALIACAO")
    private Long id_avaliacao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_AVALIACAO"),
            nullable = false
    )
    private Usuario id_usuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PEDIDO",
            referencedColumnName = "ID_PEDIDO",
            foreignKey = @ForeignKey(name = "FK_PEDIDO_AVALIACAO"),
            nullable = false
    )
    private Pedido id_pedido;

    @Column(name = "NOTA_PRECO_AVALIACAO")
    private Long nota_preco_avaliacao;

    @Column(name = "NOTA_QUALIDADE_AVALIACAO")
    private Long nota_qualidade_avaliacao;

    @Column(name = "NOTA_ENTREGA_AVALIACAO")
    private Long nota_entrega_avaliacao;

    @Column(name = "DS_AVALIACAO")
    private String ds_avaliacao;

    public Avaliacao() {
    }

    public Avaliacao(Long id_avaliacao, Usuario id_usuario, Pedido id_pedido, Long nota_preco_avaliacao, Long nota_qualidade_avaliacao, Long nota_entrega_avaliacao, String ds_avaliacao) {
        this.id_avaliacao = id_avaliacao;
        this.id_usuario = id_usuario;
        this.id_pedido = id_pedido;
        this.nota_preco_avaliacao = nota_preco_avaliacao;
        this.nota_qualidade_avaliacao = nota_qualidade_avaliacao;
        this.nota_entrega_avaliacao = nota_entrega_avaliacao;
        this.ds_avaliacao = ds_avaliacao;
    }

    public Long getId_avaliacao() {
        return id_avaliacao;
    }

    public Avaliacao setId_avaliacao(Long id_avaliacao) {
        this.id_avaliacao = id_avaliacao;
        return this;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public Avaliacao setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
        return this;
    }

    public Pedido getId_pedido() {
        return id_pedido;
    }

    public Avaliacao setId_pedido(Pedido id_pedido) {
        this.id_pedido = id_pedido;
        return this;
    }

    public Long getNota_preco_avaliacao() {
        return nota_preco_avaliacao;
    }

    public Avaliacao setNota_preco_avaliacao(Long nota_preco_avaliacao) {
        this.nota_preco_avaliacao = nota_preco_avaliacao;
        return this;
    }

    public Long getNota_qualidade_avaliacao() {
        return nota_qualidade_avaliacao;
    }

    public Avaliacao setNota_qualidade_avaliacao(Long nota_qualidade_avaliacao) {
        this.nota_qualidade_avaliacao = nota_qualidade_avaliacao;
        return this;
    }

    public Long getNota_entrega_avaliacao() {
        return nota_entrega_avaliacao;
    }

    public Avaliacao setNota_entrega_avaliacao(Long nota_entrega_avaliacao) {
        this.nota_entrega_avaliacao = nota_entrega_avaliacao;
        return this;
    }

    public String getDs_avaliacao() {
        return ds_avaliacao;
    }

    public Avaliacao setDs_avaliacao(String ds_avaliacao) {
        this.ds_avaliacao = ds_avaliacao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avaliacao avaliacao)) return false;
        return Objects.equals(id_avaliacao, avaliacao.id_avaliacao) && Objects.equals(id_usuario, avaliacao.id_usuario) && Objects.equals(id_pedido, avaliacao.id_pedido) && Objects.equals(nota_preco_avaliacao, avaliacao.nota_preco_avaliacao) && Objects.equals(nota_qualidade_avaliacao, avaliacao.nota_qualidade_avaliacao) && Objects.equals(nota_entrega_avaliacao, avaliacao.nota_entrega_avaliacao) && Objects.equals(ds_avaliacao, avaliacao.ds_avaliacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_avaliacao, id_usuario, id_pedido, nota_preco_avaliacao, nota_qualidade_avaliacao, nota_entrega_avaliacao, ds_avaliacao);
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id_avaliacao=" + id_avaliacao +
                ", id_usuario=" + id_usuario +
                ", id_pedido=" + id_pedido +
                ", nota_preco_avaliacao=" + nota_preco_avaliacao +
                ", nota_qualidade_avaliacao=" + nota_qualidade_avaliacao +
                ", nota_entrega_avaliacao=" + nota_entrega_avaliacao +
                ", ds_avaliacao='" + ds_avaliacao + '\'' +
                '}';
    }
}
