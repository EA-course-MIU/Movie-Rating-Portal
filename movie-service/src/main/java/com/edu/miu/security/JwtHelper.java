package com.edu.miu.security;

import com.edu.miu.config.vault.KeycloakConfiguration;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtHelper {

    private final KeycloakConfiguration keycloak;

    private final static String ALG_KEYCLOAK = "RSA";

    private final long expiration = 5 * 60 * 60 * 60;

    public String generateToken(String email) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, this.getPublicKey())
                .compact();
    }

    public String generateRefreshToken(String email) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 60))
                .signWith(SignatureAlgorithm.HS512, this.getPublicKey())
                .compact();
    }

    public String getSubject(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parser()
                .setSigningKey(this.getPublicKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(this.getPublicKey())
                    .parseClaimsJws(token);
            return true;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (InvalidKeySpecException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String doGenerateRefreshToken(Map<String, Object> claims, String subject) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, this.getPublicKey()).compact();
    }

    public Claims getUserIdFromToken(String token) {
        Claims result = null;
        try {
            result = Jwts.parser()
                    .setSigningKey(this.getPublicKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String rsaPublicKey = keycloak.getPublicKey();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(rsaPublicKey));
        KeyFactory kf = KeyFactory.getInstance(ALG_KEYCLOAK);
        return kf.generatePublic(keySpec);
    }
}
