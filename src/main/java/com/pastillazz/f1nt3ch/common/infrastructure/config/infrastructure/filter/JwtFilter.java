package com.pastillazz.f1nt3ch.common.infrastructure.config.infrastructure.filter;

import com.pastillazz.f1nt3ch.common.infrastructure.config.application.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");

        if (authHeader==null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt=authHeader.substring(7);
        final String username=jwtService.extractUsername(jwt);
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            if (jwtService.isTokenValid(jwt,username)){
                List <String> roles=jwtService.extractRoles(jwt);

                if (roles==null)roles=List.of("USER");
                List<SimpleGrantedAuthority> authorities=roles
                        .stream()
                        .map(r->new SimpleGrantedAuthority("ROLE_"+r.toUpperCase()))
                        .toList();
                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(username,null,authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }
        filterChain.doFilter(request, response);
    }
}
