package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Collections;
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
    @Column(name = "ID_USUARIO", columnDefinition = "NUMBER(8)")
    private Long id_usuario;

    @Column(name = "CNPJ_USUARIO", nullable = false, columnDefinition = "CHAR(18)")
    private String cnpj_usuario;

    @Column(name = "NM_USUARIO", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String nm_usuario;

    @Column(name = "CEP_USUARIO", nullable = false, columnDefinition = "CHAR(9)")
    private String cep_usuario;

    @Column(name = "LOGRADOURO_USUARIO", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String logradouro_usuario;

    @Column(name = "COMPLEMENTO_USUARIO", columnDefinition = "VARCHAR2(50)")
    private String complemento_usuario;

    @Column(name = "NUM_ENDERECO_USUARIO", nullable = false, columnDefinition = "VARCHAR2(50)")
    private String num_endereco_usuario;

    @Column(name = "EMAIL_USUARIO", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String email_usuario;

    @Column(name = "SENHA_USUARIO", nullable = false, columnDefinition = "VARCHAR2(20)")
    private String senha_usuario;

    @Column(name = "TEL_USUARIO", nullable = false, columnDefinition = "VARCHAR2(15)")
    private String tel_usuario;

    @Column(name = "E_FORNECEDOR", nullable = false, columnDefinition = "NUMBER(1)")
    private Long e_fornecedor;

    @Column(name = "IMG_URL_USUARIO", columnDefinition = "VARCHAR2(255)")
    private String img_url_usuario;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "USUARIO_TAG",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_USUARIO",
                            columnDefinition = "NUMBER(8)",
                            referencedColumnName = "ID_USUARIO",
                            foreignKey = @ForeignKey(name = "FK_USUARIO_TAG")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_TAG",
                            columnDefinition = "NUMBER(8)",
                            referencedColumnName = "ID_TAG",
                            foreignKey = @ForeignKey(name = "FK_TAG_USUARIO")
                    )
            }
    )
    private Set<Tag> tags;

    public Usuario() {
    }

    public Usuario(Long id_usuario, String cnpj_usuario, String nm_usuario, String cep_usuario, String logradouro_usuario, String complemento_usuario, String num_endereco_usuario, String email_usuario, String senha_usuario, String tel_usuario, Long e_fornecedor, String img_url_usuario, Set<Tag> tags) {
        this.id_usuario = id_usuario;
        this.cnpj_usuario = cnpj_usuario;
        this.nm_usuario = nm_usuario;
        this.cep_usuario = cep_usuario;
        this.logradouro_usuario = logradouro_usuario;
        this.complemento_usuario = complemento_usuario;
        this.num_endereco_usuario = num_endereco_usuario;
        this.email_usuario = email_usuario;
        this.senha_usuario = senha_usuario;
        this.tel_usuario = tel_usuario;
        this.e_fornecedor = e_fornecedor;
        this.img_url_usuario = img_url_usuario;
        this.tags = tags;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public Usuario setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
        return this;
    }

    public String getCnpj_usuario() {
        return cnpj_usuario;
    }

    public Usuario setCnpj_usuario(String cnpj_usuario) {
        this.cnpj_usuario = cnpj_usuario;
        return this;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public Usuario setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
        return this;
    }

    public String getCep_usuario() {
        return cep_usuario;
    }

    public Usuario setCep_usuario(String cep_usuario) {
        this.cep_usuario = cep_usuario;
        return this;
    }

    public String getLogradouro_usuario() {
        return logradouro_usuario;
    }

    public Usuario setLogradouro_usuario(String logradouro_usuario) {
        this.logradouro_usuario = logradouro_usuario;
        return this;
    }

    public String getComplemento_usuario() {
        return complemento_usuario;
    }

    public Usuario setComplemento_usuario(String complemento_usuario) {
        this.complemento_usuario = complemento_usuario;
        return this;
    }

    public String getNum_endereco_usuario() {
        return num_endereco_usuario;
    }

    public Usuario setNum_endereco_usuario(String num_endereco_usuario) {
        this.num_endereco_usuario = num_endereco_usuario;
        return this;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public Usuario setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
        return this;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public Usuario setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
        return this;
    }

    public String getTel_usuario() {
        return tel_usuario;
    }

    public Usuario setTel_usuario(String tel_usuario) {
        this.tel_usuario = tel_usuario;
        return this;
    }

    public Long getE_fornecedor() {
        return e_fornecedor;
    }

    public Usuario setE_fornecedor(Long e_fornecedor) {
        this.e_fornecedor = e_fornecedor;
        return this;
    }

    public String getImg_url_usuario() {
        return img_url_usuario;
    }

    public Usuario setImg_url_usuario(String img_url_usuario) {
        this.img_url_usuario = img_url_usuario;
        return this;
    }

    public Usuario addTag(Tag tag) {
        this.tags.add( tag );
        tag.addUsuario( this );
        return this;
    }

    public Usuario removeTag(Tag tag) {
        this.tags.remove( tag );
        if (tag.getUsuarios().equals( this )) tag.removeUsuario( this );
        return this;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet( tags );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id_usuario, usuario.id_usuario) && Objects.equals(cnpj_usuario, usuario.cnpj_usuario) && Objects.equals(nm_usuario, usuario.nm_usuario) && Objects.equals(cep_usuario, usuario.cep_usuario) && Objects.equals(logradouro_usuario, usuario.logradouro_usuario) && Objects.equals(complemento_usuario, usuario.complemento_usuario) && Objects.equals(num_endereco_usuario, usuario.num_endereco_usuario) && Objects.equals(email_usuario, usuario.email_usuario) && Objects.equals(senha_usuario, usuario.senha_usuario) && Objects.equals(tel_usuario, usuario.tel_usuario) && Objects.equals(e_fornecedor, usuario.e_fornecedor) && Objects.equals(img_url_usuario, usuario.img_url_usuario) && Objects.equals(tags, usuario.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_usuario, cnpj_usuario, nm_usuario, cep_usuario, logradouro_usuario, complemento_usuario, num_endereco_usuario, email_usuario, senha_usuario, tel_usuario, e_fornecedor, img_url_usuario, tags);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", cnpj_usuario='" + cnpj_usuario + '\'' +
                ", nm_usuario='" + nm_usuario + '\'' +
                ", cep_usuario='" + cep_usuario + '\'' +
                ", logradouro_usuario='" + logradouro_usuario + '\'' +
                ", complemento_usuario='" + complemento_usuario + '\'' +
                ", num_endereco_usuario=" + num_endereco_usuario +
                ", email_usuario='" + email_usuario + '\'' +
                ", senha_usuario='" + senha_usuario + '\'' +
                ", tel_usuario='" + tel_usuario + '\'' +
                ", e_fornecedor=" + e_fornecedor +
                ", img_url_usuario='" + img_url_usuario + '\'' +
                '}';
    }
}
