package com.kkoksal.config.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kkoksal.config.security.AuthJwtManager;
import com.kkoksal.config.security.CustomUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final AuthJwtManager authJwtManager;

    private final CustomUserService customUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasLength(authorization) || !authorization.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        DecodedJWT decodedJWT = authJwtManager.verifyJwtToken(authorization.replace("Bearer ", ""));
        if (Objects.isNull(decodedJWT)) {
            filterChain.doFilter(request, response);
            return;
        }
        UserDetails userDetails = customUserService.loadUserByUsername(decodedJWT.getSubject());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
