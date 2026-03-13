package com.lucasjacc.dev.configuration;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lucasjacc.dev.jwt.TokenService;
import com.lucasjacc.dev.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    private TokenService tokenService;
    private UserRepository repository;
    
    public SecurityFilter(TokenService tokenService, UserRepository repository){
        this.tokenService = tokenService;
        this.repository = repository;
    }
    

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("Filtro executando: " + request.getRequestURI());
        var token = recoverToken(request);
        System.out.println("TOKEN: " + token);

        if (token != null) {
            var login = tokenService.validateToken(token);
            System.out.println("LOGIN EXTRAÍDO DO TOKEN: " + login);

            if (login != null) {
                UserDetails user = repository.findByEmail(login);
                System.out.println("USUARIO DO BANCO: " + user);
                if (user != null) {
                    var authentication =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    user.getAuthorities()
                            );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
        System.out.println("Filtro terminou");
    }

    private String recoverToken(HttpServletRequest request){

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("token")) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
    
}
