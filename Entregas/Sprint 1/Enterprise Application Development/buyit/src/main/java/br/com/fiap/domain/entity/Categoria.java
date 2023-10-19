package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CATEGORIA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_CATEGORIA", columnNames = "NM_CATEGORIA")
})
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CATEGORIA")
    @SequenceGenerator(name = "SQ_CATEGORIA", sequenceName = "SQ_CATEGORIA", allocationSize = 1)
    @Column(name = "ID_CATEGORIA")
    private Long id_categoria;

    @Column(name = "NM_CATEGORIA", nullable = false)
    private String nm_categoria;

    @Column(name = "ICONE_CATEGORIA")
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

        if (!id_categoria.equals(categoria.id_categoria)) return false;
        if (!nm_categoria.equals(categoria.nm_categoria)) return false;
        return Objects.equals(icone_categoria, categoria.icone_categoria);
    }

    @Override
    public int hashCode() {
        int result = id_categoria.hashCode();
        result = 31 * result + nm_categoria.hashCode();
        result = 31 * result + (icone_categoria != null ? icone_categoria.hashCode() : 0);
        return result;
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
