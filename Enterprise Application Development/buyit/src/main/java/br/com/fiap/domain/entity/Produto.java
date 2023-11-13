package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "BUYIT_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_PRODUTO")
    @SequenceGenerator(name = "BUYIT_SQ_PRODUTO", sequenceName = "BUYIT_SQ_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NOME_PRODUTO", nullable = false)
    private String nome;

    @Column(name = "MARCA_PRODUTO")
    private String marca;

    @Column(name = "COR_PRODUTO")
    private String cor;

    @Column(name = "TAMANHO_PRODUTO")
    private String tamanho;

    @Column(name = "MATERIAL_PRODUTO")
    private String material;

    @Column(name = "OBSERVACAO_PRODUTO")
    private String observacao;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BUYIT_PRODUTO_DEPARTAMENTO",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_PRODUTO",
                            referencedColumnName = "ID_PRODUTO",
                            foreignKey = @ForeignKey(name = "FK_PRODUTO_DEPARTAMENTO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_DEPARTAMENTO",
                            referencedColumnName = "ID_DEPARTAMENTO",
                            foreignKey = @ForeignKey(name = "FK_DEPARTAMENTO_PRODUTO")
                    )
            }
    )
    private Set<Departamento> departamentos;

    public Produto() {
    }

    public Produto(Long id, String nome, String marca, String cor, String tamanho, String material, String observacao, Set<Departamento> departamentos) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.cor = cor;
        this.tamanho = tamanho;
        this.material = material;
        this.observacao = observacao;
        this.departamentos = Objects.nonNull(departamentos) ? departamentos : new LinkedHashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Produto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Produto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getMarca() {
        return marca;
    }

    public Produto setMarca(String marca) {
        this.marca = marca;
        return this;
    }

    public String getCor() {
        return cor;
    }

    public Produto setCor(String cor) {
        this.cor = cor;
        return this;
    }

    public String getTamanho() {
        return tamanho;
    }

    public Produto setTamanho(String tamanho) {
        this.tamanho = tamanho;
        return this;
    }

    public String getMaterial() {
        return material;
    }

    public Produto setMaterial(String material) {
        this.material = material;
        return this;
    }

    public String getObservacao() {
        return observacao;
    }

    public Produto setObservacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public Produto addDepartamento(Departamento departamento) {
        this.departamentos.add(departamento);
        departamento.addProduto(this);
        return this;
    }

    public Produto removeDepartamento(Departamento departamento) {
        this.departamentos.remove(departamento);
        if (departamento.getProdutos().equals(this)) departamento.removeProduto(this);
        return this;
    }

    public Set<Departamento> getDepartamentos() {
        return Collections.unmodifiableSet(departamentos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;

        if (!id.equals(produto.id)) return false;
        if (!nome.equals(produto.nome)) return false;
        if (!Objects.equals(marca, produto.marca)) return false;
        if (!Objects.equals(cor, produto.cor)) return false;
        if (!Objects.equals(tamanho, produto.tamanho)) return false;
        if (!Objects.equals(material, produto.material)) return false;
        if (!Objects.equals(observacao, produto.observacao)) return false;
        return Objects.equals(departamentos, produto.departamentos);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        result = 31 * result + (cor != null ? cor.hashCode() : 0);
        result = 31 * result + (tamanho != null ? tamanho.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (observacao != null ? observacao.hashCode() : 0);
        result = 31 * result + (departamentos != null ? departamentos.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", cor='" + cor + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", material='" + material + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
