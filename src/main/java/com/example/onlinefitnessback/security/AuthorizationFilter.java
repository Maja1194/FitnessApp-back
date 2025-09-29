package com.example.onlinefitnessback.security;

import com.example.onlinefitnessback.models.dto.JwtUser;
import com.example.onlinefitnessback.services.impl.AuthServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Value("${authorization.token.header.name}")
    private String authorizationHeaderName;

    @Value("${authorization.token.header.prefix}")
    private String authorizationHeaderPrefix;

    @Value("${authorization.token.secret}")
    private String authorizationSecret;

    private final AuthServiceImpl authService;

    @Autowired
    public AuthorizationFilter(@Lazy AuthServiceImpl authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader(authorizationHeaderName);
        logger.info(authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith(authorizationHeaderPrefix)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = authorizationHeader.replace(authorizationHeaderPrefix, "");

        try {
            Claims claims = authService.parseJWT(token);
            // Status as a String instead of Enum
            JwtUser jwtUser = new JwtUser(
                    Integer.valueOf(claims.getId()),
                    claims.getSubject(),
                    null,
                    claims.get("role", String.class) // Treat role/status as String
            );

            Authentication authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            logger.error("JWT Authentication failed from: " + httpServletRequest.getRemoteHost());
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Token is invalid or expired");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
