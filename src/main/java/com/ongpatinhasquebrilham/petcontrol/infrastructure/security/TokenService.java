package com.ongpatinhasquebrilham.petcontrol.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ongpatinhasquebrilham.petcontrol.infrastructure.security.exception.InvalidAccessTokenException;
import com.ongpatinhasquebrilham.petcontrol.infrastructure.security.exception.InvalidRefreshTokenException;
import com.ongpatinhasquebrilham.petcontrol.infrastructure.security.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class TokenService {

    @Value("${api.security.jwt.secret}")
    private String tokenSecret;
    @Value("${api.security.jwt.issuer}")
    private String tokenIssuer;
    @Value("${api.security.jwt.audience}")
    private String tokenAudience;
    @Value("${api.security.jwt.expiration}")
    private long accessTokenExpiration;
    @Value("${api.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, accessTokenExpiration, TokenType.ACCESS);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails, refreshTokenExpiration, TokenType.REFRESH);
    }

    public String getSubject(String token) throws InvalidTokenException {
        return decodeToken(token).getSubject();
    }

    public void validateAccessToken(String token) throws InvalidTokenException {
        String tokenType = decodeToken(token).getType();
        if (!tokenType.equals(TokenType.ACCESS.getValue())) {
            throw new InvalidAccessTokenException("Invalid Access Token");
        }
    }

    public void validateRefreshToken(String token) throws InvalidRefreshTokenException {
        try {
            String tokenType = decodeToken(token).getType();
            if (!tokenType.equals(TokenType.REFRESH.getValue())) {
                throw new InvalidTokenException("Invalid token");
            }
        } catch (InvalidTokenException e) {
            throw new InvalidRefreshTokenException("Invalid Refresh Token");
        }
    }

    public boolean validateToken(String token) {
        try {
            decodeToken(token);
        } catch (InvalidTokenException e) {
            return false;
        }
        return true;
    }

    private DecodedJWT decodeToken(String token) throws InvalidTokenException {
        try {
            return JWT.require(Algorithm.HMAC256(tokenSecret))
                    .withIssuer(tokenIssuer)
                    .withAudience(tokenAudience)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException("Invalid Token");
        }
    }

    private String generateToken(UserDetails userDetails, long expiration, TokenType tokenType) {
        Instant now = Instant.now();

        JWTCreator.Builder builder = JWT.create()
                .withIssuer(tokenIssuer)
                .withAudience(tokenAudience)
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withNotBefore(now)
                .withExpiresAt(now.plusMillis(expiration))
                .withHeader(Map.of("typ", tokenType.getValue()));

        if (tokenType == TokenType.ACCESS) {
            builder.withClaim("roles", String.valueOf(userDetails.getAuthorities()));
        }

        try{
            return builder.sign(Algorithm.HMAC256(tokenSecret));
        } catch(JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }
}