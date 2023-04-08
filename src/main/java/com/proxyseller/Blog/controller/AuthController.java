package com.proxyseller.Blog.controller;

import com.proxyseller.Blog.dto.request.SignInDto;
import com.proxyseller.Blog.dto.request.SignUpDto;
import com.proxyseller.Blog.dto.response.AccessTokenDto;
import com.proxyseller.Blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proxyseller.Blog.security.JwtService;

import javax.validation.Valid;
import java.time.Duration;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private static final Duration ONE_DAY = Duration.ofDays(1);
    private static final Duration ONE_WEEK = Duration.ofDays(7);
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AccessTokenDto> authorize(@Valid @RequestBody SignInDto signInDto) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(signInDto.getUsername(),
                signInDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Duration duration = getDuration(signInDto.getRememberMe());
        String jwt = jwtService.issueToken(authentication, duration);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
        return new ResponseEntity<>(new AccessTokenDto(jwt), httpHeaders, HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<Void> signUp(@RequestBody @Validated SignUpDto dto) {
        userService.Create(dto);
        return ResponseEntity.ok().build();
    }
    private Duration getDuration(Boolean rememberMe) {
        return ((rememberMe != null) && rememberMe) ? ONE_WEEK : ONE_DAY;
    }
}
