package br.com.letscode.livraria.service;

import br.com.letscode.livraria.model.dto.UsuarioDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
public class JwtService {

    private static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public Boolean valideToken(String token){

        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build().isSigned(token);
    }

    public String getUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build().parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String gerarToken(UsuarioDTO dto){

        return Jwts
                .builder()
                .setSubject(dto.getUsername())
                .setIssuer(dto.getNome())
                .setIssuedAt(new Date())
                .signWith(secretKey)
                .compact();
    }
}
