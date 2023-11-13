package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "BUYIT_DEPARTAMENTO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_NOME_DEPARTAMENTO", columnNames = "NOME_DEPARTAMENTO")
})
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_DEPARTAMENTO")
    @SequenceGenerator(name = "BUYIT_SQ_DEPARTAMENTO", sequenceName = "BUYIT_SQ_DEPARTAMENTO", allocationSize = 1)
    @Column(name = "ID_DEPARTAMENTO")
    private Long id;

    @Column(name = "NOME_DEPARTAMENTO", nullable = false)
    private String nome;

    @Column(name = "ICONE_DEPARTAMENTO")
    private String icone;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BUYIT_PRODUTO_DEPARTAMENTO",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_DEPARTAMENTO",
                            referencedColumnName = "ID_DEPARTAMENTO",
                            foreignKey = @ForeignKey(name = "FK_DEPARTAMENTO_PRODUTO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_PRODUTO",
                            referencedColumnName = "ID_PRODUTO",
                            foreignKey = @ForeignKey(name = "FK_PRODUTO_DEPARTAMENTO")
                    )
            }
    )
    private Set<Produto> produtos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BUYIT_TAG_DEPARTAMENTO",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_DEPARTAMENTO",
                            referencedColumnName = "ID_DEPARTAMENTO",
                            foreignKey = @ForeignKey(name = "FK_DEPARTAMENTO_TAG")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_TAG",
                            referencedColumnName = "ID_TAG",
                            foreignKey = @ForeignKey(name = "FK_TAG_DEPARTAMENTO")
                    )
            }
    )
    private Set<Tag> tags;

    public Departamento() {
    }

    public Departamento(Long id, String nome, String icone, Set<Produto> produtos, Set<Tag> tags) {
        this.id = id;
        this.nome = nome;
        this.icone = icone;
        this.produtos = Objects.nonNull(produtos) ? produtos : new LinkedHashSet<>();
        this.tags = Objects.nonNull(tags) ? tags : new LinkedHashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Departamento setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Departamento setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getIcone() {
        return icone;
    }

    public Departamento setIcone(String icone) {
        this.icone = icone;
        return this;
    }

    public Departamento addProduto(Produto produto) {
        this.produtos.add(produto);
        produto.addDepartamento(this);
        return this;
    }

    public Departamento removeProduto(Produto produto) {
        this.produtos.remove(produto);
        if (produto.getDepartamentos().equals(this)) produto.removeDepartamento(this);
        return this;
    }

    public Set<Produto> getProdutos() {
        return Collections.unmodifiableSet(produtos);
    }

    public Departamento addTag(Tag tag) {
        this.tags.add(tag);
        tag.addDepartamento(this);
        return this;
    }

    public Departamento removeTag(Tag tag) {
        this.tags.remove(tag);
        if (tag.getDepartamentos().equals(this)) tag.removeDepartamento(this);
        return this;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento that)) return false;

        if (!id.equals(that.id)) return false;
        if (!nome.equals(that.nome)) return false;
        if (!Objects.equals(icone, that.icone)) return false;
        if (!Objects.equals(produtos, that.produtos)) return false;
        return Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + (icone != null ? icone.hashCode() : 0);
        result = 31 * result + (produtos != null ? produtos.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", icone='" + icone + '\'' +
                '}';
    }
}
