package br.com.fiap.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BUYIT_CONTATO")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYIT_SQ_CONTATO")
    @SequenceGenerator(name = "BUYIT_SQ_CONTATO", sequenceName = "BUYIT_SQ_CONTATO", allocationSize = 1)
    @Column(name = "ID_CONTATO")
    private Long id;

    @Column(name = "NOME_CONTATO", nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_CONTATO"),
            nullable = false
    )
    private Usuario usuario;

    public Contato() {
    }

    public Contato(Long id, String nome, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public Contato setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Contato setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Contato setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contato contato)) return false;

        if (!id.equals(contato.id)) return false;
        if (!nome.equals(contato.nome)) return false;
        return usuario.equals(contato.usuario);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + usuario.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
