package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "BUYIT_CATEGORIA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_NM_CATEGORIA", columnNames = "NM_CATEGORIA")
})
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CATEGORIA")
    @SequenceGenerator(name = "SQ_CATEGORIA", sequenceName = "SQ_CATEGORIA", allocationSize = 1)
    @Column(name = "ID_CATEGORIA")
    private Long id;

    @Column(name = "NM_CATEGORIA", nullable = false)
    private String nome;

    @Column(name = "ICONE_CATEGORIA")
    private String icone;

    public Categoria() {
    }

    public Categoria(Long id, String nome, String icone) {
        this.id = id;
        this.nome = nome;
        this.icone = icone;
    }

    public Long getId() {
        return id;
    }

    public Categoria setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Categoria setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getIcone() {
        return icone;
    }

    public Categoria setIcone(String icone) {
        this.icone = icone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria categoria)) return false;
        return Objects.equals(id, categoria.id) && Objects.equals(nome, categoria.nome) && Objects.equals(icone, categoria.icone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, icone);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", icone='" + icone + '\'' +
                '}';
    }
}
