package br.com.fiap.domain.entity;

import jakarta.json.bind.annotation.JsonbTransient;
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
    @Column(name = "ID_PEDIDO")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_PEDIDO"),
            nullable = false
    )
    private Usuario usuario;

    @Column(name = "STATUS_PEDIDO", nullable = false)
    private String status;

    @Column(name = "DATA_PEDIDO", nullable = false)
    private LocalDate data;

    @Column(name = "VALOR_PEDIDO", nullable = false)
    private BigDecimal valor;

    @JsonbTransient
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BUYIT_PEDIDO_ESTOQUE",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_PEDIDO",
                            referencedColumnName = "ID_PEDIDO",
                            foreignKey = @ForeignKey(name = "FK_PEDIDO_ESTOQUE")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_ESTOQUE",
                            referencedColumnName = "ID_ESTOQUE",
                            foreignKey = @ForeignKey(name = "FK_ESTOQUE_PEDIDO")
                    )
            }
    )
    private Set<Estoque> estoques;

    public Pedido() {
    }

    public Pedido(Long id, Usuario usuario, String status, LocalDate data, BigDecimal valor, Set<Estoque> estoques) {
        this.id = id;
        this.usuario = usuario;
        this.status = status;
        this.data = data;
        this.valor = valor;
        this.estoques = Objects.nonNull(estoques) ? estoques : new LinkedHashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Pedido setId(Long id) {
        this.id = id;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Pedido setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Pedido setStatus(String status) {
        this.status = status;
        return this;
    }

    public LocalDate getData() {
        return data;
    }

    public Pedido setData(LocalDate data) {
        this.data = data;
        return this;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Pedido setValor(BigDecimal valor) {
        this.valor = valor;
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
        return Objects.equals(id, pedido.id) && Objects.equals(usuario, pedido.usuario) && Objects.equals(status, pedido.status) && Objects.equals(data, pedido.data) && Objects.equals(valor, pedido.valor) && Objects.equals(estoques, pedido.estoques);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, status, data, valor, estoques);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", status='" + status + '\'' +
                ", data=" + data +
                ", valor=" + valor +
                '}';
    }
}
