package br.com.fiap.domain.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "BUYIT_USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BUYIT_CNPJ_USUARIO", columnNames = "CNPJ_USUARIO"),
        @UniqueConstraint(name = "UK_BUYIT_EMAIL_USUARIO", columnNames = "EMAIL_USUARIO"),
        @UniqueConstraint(name = "UK_BUYIT_TEL_USUARIO", columnNames = "TEL_USUARIO")
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "CNPJ_USUARIO", nullable = false)
    private String cnpj;

    @Column(name = "NM_USUARIO", nullable = false)
    private String nome;

    @Column(name = "CEP_USUARIO", nullable = false)
    private String cep;

    @Column(name = "LOGRADOURO_USUARIO", nullable = false)
    private String logradouro;

    @Column(name = "COMPLEMENTO_USUARIO")
    private String complemento;

    @Column(name = "NUM_ENDERECO_USUARIO", nullable = false)
    private String numEndereco;

    @Column(name = "EMAIL_USUARIO", nullable = false)
    private String email;

    @Column(name = "SENHA_USUARIO", nullable = false)
    private String senha;

    @Column(name = "TEL_USUARIO", nullable = false)
    private String tel;

    @Column(name = "E_FORNECEDOR", nullable = false)
    private Long e_fornecedor;

    @Column(name = "IMG_URL_USUARIO")
    private String imgUrl;

    @Column(name = "REGIME_TRIBUTARIO_USUARIO", nullable = false)
    private String regimeTributario;

    @Column(name = "VALOR_MAX_AUTOMATICO_USUARIO", nullable = false)
    private BigDecimal compraAutomatica;

    @JsonbTransient
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BUYIT_USUARIO_TAG",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_USUARIO",
                            referencedColumnName = "ID_USUARIO",
                            foreignKey = @ForeignKey(name = "FK_USUARIO_TAG")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_TAG",
                            referencedColumnName = "ID_TAG",
                            foreignKey = @ForeignKey(name = "FK_TAG_USUARIO")
                    )
            }
    )
    private Set<Tag> tags;

    public Usuario() {
    }

    public Usuario(Long id, String cnpj, String nome, String cep, String logradouro, String complemento, String numEndereco, String email, String senha, String tel, Long e_fornecedor, String imgUrl, Set<Tag> tags, String regimeTributario, BigDecimal compraAutomatica) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numEndereco = numEndereco;
        this.email = email;
        this.senha = senha;
        this.tel = tel;
        this.e_fornecedor = e_fornecedor;
        this.imgUrl = imgUrl;
        this.regimeTributario = regimeTributario;
        this.compraAutomatica = compraAutomatica;
        this.tags = Objects.nonNull(tags) ? tags : new LinkedHashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Usuario setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Usuario setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public Usuario setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Usuario setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Usuario setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getNumEndereco() {
        return numEndereco;
    }

    public Usuario setNumEndereco(String numEndereco) {
        this.numEndereco = numEndereco;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public Usuario setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public Long getE_fornecedor() {
        return e_fornecedor;
    }

    public Usuario setE_fornecedor(Long e_fornecedor) {
        this.e_fornecedor = e_fornecedor;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Usuario setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getRegimeTributario() {
        return regimeTributario;
    }

    public Usuario setRegimeTributario(String regimeTributario) {
        this.regimeTributario = regimeTributario;
        return this;
    }

    public BigDecimal getCompraAutomatica() {
        return compraAutomatica;
    }

    public Usuario setCompraAutomatica(BigDecimal compraAutomatica) {
        this.compraAutomatica = compraAutomatica;
        return this;
    }

    public Usuario addTag(Tag tag) {
        this.tags.add(tag);
        tag.addUsuario(this);
        return this;
    }

    public Usuario removeTag(Tag tag) {
        this.tags.remove(tag);
        if (tag.getUsuarios().equals(this)) tag.removeUsuario(this);
        return this;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id) && Objects.equals(cnpj, usuario.cnpj) && Objects.equals(nome, usuario.nome) && Objects.equals(cep, usuario.cep) && Objects.equals(logradouro, usuario.logradouro) && Objects.equals(complemento, usuario.complemento) && Objects.equals(numEndereco, usuario.numEndereco) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(tel, usuario.tel) && Objects.equals(e_fornecedor, usuario.e_fornecedor) && Objects.equals(imgUrl, usuario.imgUrl) && Objects.equals(regimeTributario, usuario.regimeTributario) && Objects.equals(compraAutomatica, usuario.compraAutomatica) && Objects.equals(tags, usuario.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj, nome, cep, logradouro, complemento, numEndereco, email, senha, tel, e_fornecedor, imgUrl, regimeTributario, compraAutomatica, tags);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", numEndereco='" + numEndereco + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", tel='" + tel + '\'' +
                ", e_fornecedor=" + e_fornecedor +
                ", imgUrl='" + imgUrl + '\'' +
                ", regimeTributario='" + regimeTributario + '\'' +
                ", compraAutomatica=" + compraAutomatica +
                '}';
    }
}
