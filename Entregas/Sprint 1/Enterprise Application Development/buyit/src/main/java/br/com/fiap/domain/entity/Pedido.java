package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PEDIDO")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO")
    @SequenceGenerator(name = "SQ_PEDIDO", sequenceName = "SQ_PEDIDO", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_PEDIDO")
    private Long id_pedido;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_AVALIACAO"),
            nullable = false
    )
    private Usuario id_usuario;

    @Column(name = "STATUS_PEDIDO", nullable = false)
    private String status_pedido;

    @Column(name = "DATA_PEDIDO", nullable = false)
    private LocalDate data_pedido;

    @Column(name = "VALOR_PEDIDO", nullable = false)
    private BigDecimal valor_pedido;

    public Pedido() {
    }

    public Pedido(Long id_pedido, Usuario id_usuario, String status_pedido, LocalDate data_pedido, BigDecimal valor_pedido) {
        this.id_pedido = id_pedido;
        this.id_usuario = id_usuario;
        this.status_pedido = status_pedido;
        this.data_pedido = data_pedido;
        this.valor_pedido = valor_pedido;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public Pedido setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
        return this;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public Pedido setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
        return this;
    }

    public String getStatus_pedido() {
        return status_pedido;
    }

    public Pedido setStatus_pedido(String status_pedido) {
        this.status_pedido = status_pedido;
        return this;
    }

    public LocalDate getData_pedido() {
        return data_pedido;
    }

    public Pedido setData_pedido(LocalDate data_pedido) {
        this.data_pedido = data_pedido;
        return this;
    }

    public BigDecimal getValor_pedido() {
        return valor_pedido;
    }

    public Pedido setValor_pedido(BigDecimal valor_pedido) {
        this.valor_pedido = valor_pedido;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido pedido)) return false;

        if (!id_pedido.equals(pedido.id_pedido)) return false;
        if (!id_usuario.equals(pedido.id_usuario)) return false;
        if (!status_pedido.equals(pedido.status_pedido)) return false;
        if (!data_pedido.equals(pedido.data_pedido)) return false;
        return valor_pedido.equals(pedido.valor_pedido);
    }

    @Override
    public int hashCode() {
        int result = id_pedido.hashCode();
        result = 31 * result + id_usuario.hashCode();
        result = 31 * result + status_pedido.hashCode();
        result = 31 * result + data_pedido.hashCode();
        result = 31 * result + valor_pedido.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id_pedido=" + id_pedido +
                ", id_usuario=" + id_usuario +
                ", status_pedido='" + status_pedido + '\'' +
                ", data_pedido=" + data_pedido +
                ", valor_pedido=" + valor_pedido +
                '}';
    }
}
