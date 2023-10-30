package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "BUYIT_LOG")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LOG")
    @SequenceGenerator(name = "SQ_LOG", sequenceName = "SQ_LOG", allocationSize = 1)
    @Column(name = "ID_LOG")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PEDIDO",
            referencedColumnName = "ID_PEDIDO",
            foreignKey = @ForeignKey(name = "FK_PEDIDO_LOG"),
            nullable = false
    )
    private Pedido pedido;

    @Column(name = "TIMESTAMP_LOG", nullable = false)
    private String timestamp;

    @Column(name = "NM_LOG", nullable = false)
    private String nome;

    @Column(name = "DS_LOG", nullable = false)
    private String descricao;

    public Log() {
    }

    public Log(Long id, Pedido pedido, String timestamp, String nome, String descricao) {
        this.id = id;
        this.pedido = pedido;
        this.timestamp = timestamp;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public Log setId(Long id) {
        this.id = id;
        return this;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Log setPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Log setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Log setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Log setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log log)) return false;
        return Objects.equals(id, log.id) && Objects.equals(pedido, log.pedido) && Objects.equals(timestamp, log.timestamp) && Objects.equals(nome, log.nome) && Objects.equals(descricao, log.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedido, timestamp, nome, descricao);
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", pedido=" + pedido +
                ", timestamp='" + timestamp + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
