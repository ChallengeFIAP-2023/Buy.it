package br.com.fiap.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TAG", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_TAG", columnNames = "NM_TAG")
})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TAG")
    @SequenceGenerator(name = "SQ_TAG", sequenceName = "SQ_TAG", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_TAG")
    private Long id_tag;

    @Column(name = "NM_TAG", nullable = false)
    private String nm_tag;

    public Tag() {
    }

    public Tag(Long id_tag, String nm_tag) {
        this.id_tag = id_tag;
        this.nm_tag = nm_tag;
    }

    public Long getId_tag() {
        return id_tag;
    }

    public Tag setId_tag(Long id_tag) {
        this.id_tag = id_tag;
        return this;
    }

    public String getNm_tag() {
        return nm_tag;
    }

    public Tag setNm_tag(String nm_tag) {
        this.nm_tag = nm_tag;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;

        if (!id_tag.equals(tag.id_tag)) return false;
        return nm_tag.equals(tag.nm_tag);
    }

    @Override
    public int hashCode() {
        int result = id_tag.hashCode();
        result = 31 * result + nm_tag.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id_tag=" + id_tag +
                ", nm_tag='" + nm_tag + '\'' +
                '}';
    }
}
