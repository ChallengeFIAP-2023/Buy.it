package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "BUYIT_TELEFONE")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_TELEFONE")
    @SequenceGenerator(name = "BUYIT_SQ_TELEFONE", sequenceName = "BUYIT_SQ_TELEFONE", allocationSize = 1)
    @Column(name = "ID_TELEFONE")
    private Long id;

    @Column(name = "DDI_TELEFONE", nullable = false)
    private String ddi;

    @Column(name = "DDD_TELEFONE")
    private String ddd;

    @Column(name = "NUMERO_TELEFONE", nullable = false)
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_CONTATO",
            referencedColumnName = "ID_CONTATO",
            foreignKey = @ForeignKey(name = "FK_CONTATO_TELEFONE"),
            nullable = false
    )
    private Contato contato;

    public Telefone() {
    }

    public Telefone(Long id, String ddi, String ddd, String numero, Contato contato) {
        this.id = id;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
        this.contato = contato;
    }

    public Long getId() {
        return id;
    }

    public Telefone setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDdi() {
        return ddi;
    }

    public Telefone setDdi(String ddi) {
        this.ddi = ddi;
        return this;
    }

    public String getDdd() {
        return ddd;
    }

    public Telefone setDdd(String ddd) {
        this.ddd = ddd;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public Telefone setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public Contato getContato() {
        return contato;
    }

    public Telefone setContato(Contato contato) {
        this.contato = contato;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telefone telefone)) return false;

        if (!id.equals(telefone.id)) return false;
        if (!ddi.equals(telefone.ddi)) return false;
        if (!Objects.equals(ddd, telefone.ddd)) return false;
        if (!numero.equals(telefone.numero)) return false;
        return contato.equals(telefone.contato);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + ddi.hashCode();
        result = 31 * result + (ddd != null ? ddd.hashCode() : 0);
        result = 31 * result + numero.hashCode();
        result = 31 * result + contato.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", ddi='" + ddi + '\'' +
                ", ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                ", contato=" + contato +
                '}';
    }
}
