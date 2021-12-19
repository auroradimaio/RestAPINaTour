package com.example.NaTour21.Filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User)authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("NaTour21SecretKey".getBytes());
        String accesToken = JWT.create()
                .withSubject(request.getParameter("email"))
                .withExpiresAt(new Date(System.currentTimeMillis()+ 3600 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refreshToken = JWT.create()
                .withSubject(request.getParameter("email"))
                .withExpiresAt(new Date(System.currentTimeMillis() + 3 * 3600 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accesToken);
        tokens.put("refreshToken", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), new BasicResponse(tokens, "OK"));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        new BasicResponse("Credenziali invalide", "FAILED");
    }

}
