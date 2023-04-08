package com.proxyseller.Blog.security.service;

import com.proxyseller.Blog.security.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

import static com.proxyseller.Blog.config.HttpConstants.AUTH_HEADER;
import static com.proxyseller.Blog.config.HttpConstants.BEARER_AUTH_HEADER;


@Slf4j
@Service
@RequiredArgsConstructor
public final class TokenAuthService implements AuthService {

    private final JwtService jwtService;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest req) {
        return Optional.ofNullable(req.getHeader(AUTH_HEADER))
                .filter(this::isBearerAuth)
                .flatMap(this::getAuthenticationBearer);
    }

    private boolean isBearerAuth(String header) {
        return header.toLowerCase().startsWith(BEARER_AUTH_HEADER.toLowerCase());
    }

    private Optional<Authentication> getAuthenticationBearer(String header) {
        String token = header.substring(BEARER_AUTH_HEADER.length()).trim();
        Claims claims = jwtService.parseToken(token);
        if (claims.getExpiration().before(new Date())) {
            return Optional.empty();
        }
        return Optional.of(getAuthenticationBearer(claims));
    }

    private Authentication getAuthenticationBearer(Claims claims) {
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, null);
    }

}
