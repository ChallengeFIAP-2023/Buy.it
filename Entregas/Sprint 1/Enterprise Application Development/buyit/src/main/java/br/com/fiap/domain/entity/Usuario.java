package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CNPJ_USUARIO", columnNames = "CNPJ_USUARIO"),
        @UniqueConstraint(name = "EMAIL_USUARIO", columnNames = "EMAIL_USUARIO"),
        @UniqueConstraint(name = "TEL_USUARIO", columnNames = "TEL_USUARIO")
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long id_usuario;

    @Column(name = "CNPJ_USUARIO", nullable = false)
    private String cnpj_usuario;

    @Column(name = "NM_USUARIO", nullable = false)
    private String nm_usuario;

    @Column(name = "CEP_USUARIO", nullable = false)
    private String cep_usuario;

    @Column(name = "LOGRADOURO_USUARIO", nullable = false)
    private String logradouro_usuario;

    @Column(name = "COMPLEMENTO_USUARIO")
    private String complemento_usuario;

    @Column(name = "NUM_ENDERECO_USUARIO", nullable = false)
    private Long num_endereco_usuario;

    @Column(name = "EMAIL_USUARIO", nullable = false)
    private String email_usuario;

    @Column(name = "SENHA_USUARIO", nullable = false)
    private String senha_usuario;

    @Column(name = "TEL_USUARIO", nullable = false)
    private String tel_usuario;

    @Column(name = "E_FORNECEDOR", nullable = false)
    private Long e_fornecedor;

    @Column(name = "IMG_URL_USUARIO")
    private String img_url_usuario;

    public Usuario() {
    }

    public Usuario(Long id_usuario, String cnpj_usuario, String nm_usuario, String cep_usuario, String logradouro_usuario, String complemento_usuario, Long num_endereco_usuario, String email_usuario, String senha_usuario, String tel_usuario, Long e_fornecedor, String img_url_usuario) {
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

    public Long getNum_endereco_usuario() {
        return num_endereco_usuario;
    }

    public Usuario setNum_endereco_usuario(Long num_endereco_usuario) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;

        if (!id_usuario.equals(usuario.id_usuario)) return false;
        if (!cnpj_usuario.equals(usuario.cnpj_usuario)) return false;
        if (!nm_usuario.equals(usuario.nm_usuario)) return false;
        if (!cep_usuario.equals(usuario.cep_usuario)) return false;
        if (!logradouro_usuario.equals(usuario.logradouro_usuario)) return false;
        if (!Objects.equals(complemento_usuario, usuario.complemento_usuario))
            return false;
        if (!num_endereco_usuario.equals(usuario.num_endereco_usuario)) return false;
        if (!email_usuario.equals(usuario.email_usuario)) return false;
        if (!senha_usuario.equals(usuario.senha_usuario)) return false;
        if (!tel_usuario.equals(usuario.tel_usuario)) return false;
        if (!e_fornecedor.equals(usuario.e_fornecedor)) return false;
        return Objects.equals(img_url_usuario, usuario.img_url_usuario);
    }

    @Override
    public int hashCode() {
        int result = id_usuario.hashCode();
        result = 31 * result + cnpj_usuario.hashCode();
        result = 31 * result + nm_usuario.hashCode();
        result = 31 * result + cep_usuario.hashCode();
        result = 31 * result + logradouro_usuario.hashCode();
        result = 31 * result + (complemento_usuario != null ? complemento_usuario.hashCode() : 0);
        result = 31 * result + num_endereco_usuario.hashCode();
        result = 31 * result + email_usuario.hashCode();
        result = 31 * result + senha_usuario.hashCode();
        result = 31 * result + tel_usuario.hashCode();
        result = 31 * result + e_fornecedor.hashCode();
        result = 31 * result + (img_url_usuario != null ? img_url_usuario.hashCode() : 0);
        return result;
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
