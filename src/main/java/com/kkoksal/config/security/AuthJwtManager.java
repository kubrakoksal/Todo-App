package com.kkoksal.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthJwtManager {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration_ms}")
    private Long jwtExpirationMs;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    public String generateJwtToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .withClaim("ROLE", "ADMIN")
                .withIssuedAt(new Date())
                .withIssuer(jwtIssuer)
                .withSubject(userPrincipal.getUsername())
                .sign(algorithm);

    }

    public DecodedJWT verifyJwtToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .withIssuer(jwtIssuer)
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        if (isTokenExpired(decodedJWT)) {
            return null;
        }
        return decodedJWT;
    }

    private boolean isTokenExpired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(new Date(System.currentTimeMillis()));
    }

}
