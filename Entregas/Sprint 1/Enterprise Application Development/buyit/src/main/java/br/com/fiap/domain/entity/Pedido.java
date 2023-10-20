package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "BUYIT_PEDIDO")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO")
    @SequenceGenerator(name = "SQ_PEDIDO", sequenceName = "SQ_PEDIDO", allocationSize = 1)
    @Column(name = "ID_PEDIDO", columnDefinition = "NUMBER(8)")
    private Long id_pedido;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_USUARIO",
            columnDefinition = "NUMBER(8)",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_AVALIACAO"),
            nullable = false
    )
    private Usuario id_usuario;

    @Column(name = "STATUS_PEDIDO", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String status_pedido;

    @Column(name = "DATA_PEDIDO", nullable = false, columnDefinition = "DATE")
    private LocalDate data_pedido;

    @Column(name = "VALOR_PEDIDO", nullable = false, columnDefinition = "NUMBER(10,2)")
    private BigDecimal valor_pedido;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "PEDIDO_ESTOQUE",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_PEDIDO",
                            columnDefinition = "NUMBER(8)",
                            referencedColumnName = "ID_PEDIDO",
                            foreignKey = @ForeignKey(name = "FK_PEDIDO_ESTOQUE")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_ESTOQUE",
                            columnDefinition = "NUMBER(8)",
                            referencedColumnName = "ID_ESTOQUE",
                            foreignKey = @ForeignKey(name = "FK_ESTOQUE_PEDIDO")
                    )
            }
    )
    private Set<Estoque> estoques;

    public Pedido() {
    }

    public Pedido(Long id_pedido, Usuario id_usuario, String status_pedido, LocalDate data_pedido, BigDecimal valor_pedido, Set<Estoque> estoques) {
        this.id_pedido = id_pedido;
        this.id_usuario = id_usuario;
        this.status_pedido = status_pedido;
        this.data_pedido = data_pedido;
        this.valor_pedido = valor_pedido;
        this.estoques = Objects.nonNull(estoques) ? estoques : new LinkedHashSet<>();
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

    public Pedido addEstoque(Estoque estoque) {
        this.estoques.add(estoque);
        estoque.addPedido(this);
        return this;
    }

    public Pedido removeEstoque(Estoque estoque) {
        this.estoques.remove(estoque);
        if (estoque.getPedidos().equals(this)) estoque.removePedido(this);
        return this;
    }

    public Set<Estoque> getEstoques() {
        return Collections.unmodifiableSet(estoques);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido pedido)) return false;
        return Objects.equals(id_pedido, pedido.id_pedido) && Objects.equals(id_usuario, pedido.id_usuario) && Objects.equals(status_pedido, pedido.status_pedido) && Objects.equals(data_pedido, pedido.data_pedido) && Objects.equals(valor_pedido, pedido.valor_pedido) && Objects.equals(estoques, pedido.estoques);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pedido, id_usuario, status_pedido, data_pedido, valor_pedido, estoques);
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
