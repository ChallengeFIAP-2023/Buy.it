package br.com.fiap.buy.it.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL_USUARIO", columnNames = "EMAIL_USUARIO"),
        @UniqueConstraint(name = "UK_CNPJ_USUARIO", columnNames = "CNPJ_USUARIO")
})
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOME_USUARIO", nullable = false)
    @NotBlank(message = "O campo nome não pode estar vazio.")
    private String nome;

    @Column(name = "EMAIL_USUARIO", nullable = false)
    @NotBlank(message = "O campo email não pode estar vazio.")
    @Email(message = "Endereço de e-mail inválido.")
    private String email;

    @Column(name = "SENHA_USUARIO", nullable = false)
    @NotBlank(message = "O campo senha não pode estar vazio.")
    private String senha;

    @Column(name = "IMAGEM_USUARIO")
    private String urlImagem;

    @Column(name = "CNPJ_USUARIO", nullable = false)
    @NotBlank(message = "O campo cnpj não pode estar vazio.")
    private String cnpj;

    @Column(name = "IS_FORNECEDOR", nullable = false)
    @NotNull(message = "O campo isFornecedor não pode estar vazio.")
    private Boolean isFornecedor;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "USUARIO_TAG",
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
    private Set<Tag> tags = new LinkedHashSet<>();

    public Usuario addTag(Tag tag) {
        this.tags.add(tag);
        tag.addUsuario(this);
        return this;
    }

    public Usuario removeTag(Tag tag){
        this.tags.remove(tag);
        if (tag.getUsuarios().equals(this)) tag.removeUsuario(this);
        return this;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, null, getAuthorities());
    }
}