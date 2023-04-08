package com.proxyseller.Blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public final class JwtService {
    private final SecurityProperties applicationProperties;
    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        keyBytes = Decoders.BASE64.decode(applicationProperties.getJwtProperties().getSecret());
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String issueToken(Authentication authentication, Duration duration) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuer("www.proxyseller.com")
                .setIssuedAt(new Date())
                .setExpiration((Date.from(Instant.now().plus(duration))))
                .setHeader(Map.of("type", "JWT"))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
