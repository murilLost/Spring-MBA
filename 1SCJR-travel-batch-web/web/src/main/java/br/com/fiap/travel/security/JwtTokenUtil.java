package br.com.fiap.travel.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret; //Hash para gerar o JWT, como se fosse uma senha criptografada

    @Value("${jwt.expirationInSeconds}")
    private int expirationInSeconds;//Tempo para expirar o JWT gerado

    public String generateToken(String username){ //Gera o JWT, recebendo o username, cuidado para não colocar dados sensíveis do usuário, não pode ir no JWT
        LocalDateTime now = LocalDateTime.now();
        Date issuedAt = Date.from(now.toInstant(OffsetDateTime.now().getOffset()));
        Date expiration = Date.from(now.plusSeconds(expirationInSeconds).toInstant(OffsetDateTime.now().getOffset()));

        return Jwts.builder()
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setSubject(username)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String getUsernameFromToken(String token){ //Recebe o token e atráves da assinatura(secret), ele pega os dados desse token(converte ele).
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Date dateFromLocalDateTime(LocalDateTime now) {
        return Date.from(now.toInstant(OffsetDateTime.now().getOffset()));
    }


}
