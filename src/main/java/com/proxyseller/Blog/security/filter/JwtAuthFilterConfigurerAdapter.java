package com.proxyseller.Blog.security.filter;

import com.proxyseller.Blog.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@RequiredArgsConstructor
public class JwtAuthFilterConfigurerAdapter extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final List<AuthService> authServices;

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(new JwtAuthRequestFilter(authServices), UsernamePasswordAuthenticationFilter.class);
    }
}
