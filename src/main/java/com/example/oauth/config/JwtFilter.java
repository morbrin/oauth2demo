package com.example.oauth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String BEARER_HEADER = "Bearer";
    private static final String REQUEST_HEADER = "Authorization";
    private static final String ROLE = "ROLE_";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader(REQUEST_HEADER);

        if (header != null && header.startsWith(BEARER_HEADER)) {
            String token = header.substring(7);

            String username = JwtUtils.validateAndGetUsername(token);
            String role = JwtUtils.getRole(token);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    List.of(new SimpleGrantedAuthority(ROLE + role))
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
