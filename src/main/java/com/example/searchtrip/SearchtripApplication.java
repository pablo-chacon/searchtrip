package com.example.searchtrip;

import com.example.searchtrip.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@SpringBootApplication
public class SearchtripApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchtripApplication.class, args);
    }

    @Bean
    public OncePerRequestFilter authFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain) throws ServletException, IOException {

                if(request.getMethod().equals("OPTIONS")) {
                    response.setStatus(HttpServletResponse.SC_OK);
                }
                else if(!request.getServletPath().contains("search")) {
                    filterChain.doFilter(request, response);
                }
                else {
                    String authHeader = request.getHeader("authorization");
                    if(authHeader == null) {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    }
                    else {
                        String token = authHeader.replace("Bearer ", "");
                        try {
                            JwtUtil.verify(token);
                            filterChain.doFilter(request, response);
                        } catch(SignatureException ex) {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        } catch(ExpiredJwtException | MalformedJwtException ex) {
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    }
                }
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}


