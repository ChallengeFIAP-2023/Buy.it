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
    @Column(name = "ID_CATEGORIA", columnDefinition = "NUMBER(8)")
    private Long id_categoria;

    @Column(name = "NM_CATEGORIA", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String nm_categoria;

    @Column(name = "ICONE_CATEGORIA", columnDefinition = "VARCHAR2(255)")
    private String icone_categoria;

    public Categoria() {
    }

    public Categoria(Long id_categoria, String nm_categoria, String icone_categoria) {
        this.id_categoria = id_categoria;
        this.nm_categoria = nm_categoria;
        this.icone_categoria = icone_categoria;
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public Categoria setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
        return this;
    }

    public String getNm_categoria() {
        return nm_categoria;
    }

    public Categoria setNm_categoria(String nm_categoria) {
        this.nm_categoria = nm_categoria;
        return this;
    }

    public String getIcone_categoria() {
        return icone_categoria;
    }

    public Categoria setIcone_categoria(String icone_categoria) {
        this.icone_categoria = icone_categoria;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria categoria)) return false;
        return Objects.equals(id_categoria, categoria.id_categoria) && Objects.equals(nm_categoria, categoria.nm_categoria) && Objects.equals(icone_categoria, categoria.icone_categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_categoria, nm_categoria, icone_categoria);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id_categoria=" + id_categoria +
                ", nm_categoria='" + nm_categoria + '\'' +
                ", icone_categoria='" + icone_categoria + '\'' +
                '}';
    }
}
