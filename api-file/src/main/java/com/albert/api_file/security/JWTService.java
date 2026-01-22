package com.albert.api_file.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JWTService {
    private final Algorithm algorithm = Algorithm.HMAC256("my-secret-string-no-one-shall-ever-find");
    private final JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("auth0")
            .build();

    public String generateToken(UUID userId){
        return JWT.create()
                .withIssuer("auth0")
                .withSubject(userId.toString())
                .sign(algorithm);
    }

    public UUID verifyToken(String token){
        DecodedJWT decodedJWT = verifier.verify(token);
        String idString = decodedJWT.getSubject();
        return UUID.fromString(idString);
    }
}
