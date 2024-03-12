package br.com.fiap.buy.it.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.buy.it.dto.Token;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.UsuarioRepository;

@Service
public class TokenService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    public Token generateToken(String email){
        Algorithm alg = Algorithm.HMAC512("secretbuyit");
        var jwt = JWT.create()
            .withIssuer("Buyit")
            .withSubject(email)
            .withExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS))
            .sign(alg);
    
        return new Token(jwt, "JWT", "Bearer");
    }

    public Usuario validateToken(String token){
        Algorithm alg = Algorithm.HMAC512("secretbuyit");
        String email = JWT.require(alg)
            .withIssuer("Buyit")
            .build()
            .verify(token)
            .getSubject();

        return usuarioRepository
            .findByEmail(email)
            .orElseThrow(() -> new JWTVerificationException("Erro na validação do Token."));
    }
}