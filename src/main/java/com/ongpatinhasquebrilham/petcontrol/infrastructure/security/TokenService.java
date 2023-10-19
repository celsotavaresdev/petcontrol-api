package com.ongpatinhasquebrilham.petcontrol.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ongpatinhasquebrilham.petcontrol.infrastructure.security.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.jwt.secret}")
    private String tokenSecret;
    @Value("${api.security.jwt.issuer}")
    private String tokenIssuer;
    @Value("${api.security.jwt.expiration}")
    private long accessTokenExpiration;

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, accessTokenExpiration);
    }

    public String validateToken(String token) throws InvalidTokenException {
        try {
            return JWT.require(Algorithm.HMAC256(tokenSecret))
                    .withIssuer(tokenIssuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException("Invalid Token");
        }
    }

    private String generateToken(UserDetails userDetails, long expiration) {
        Instant now = Instant.now();
        try{
            return JWT.create()
                    .withSubject(userDetails.getUsername())
                    .withIssuer(tokenIssuer)
                    .withIssuedAt(now)
                    .withExpiresAt(now.plusMillis(expiration))
                    .sign(Algorithm.HMAC256(tokenSecret));
        } catch(JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }
}