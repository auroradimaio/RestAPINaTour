package com.example.NaTour21.User.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.NaTour21.User.Entity.User;
import com.example.NaTour21.User.Service.UserService;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<BasicResponse>getUsers() {
        BasicResponse response = new BasicResponse(userService.getUsers().toArray(), "OK");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/register")
    public ResponseEntity<BasicResponse>saveUser(@RequestBody User user)
    {
        BasicResponse response = null;
        try {
            response = new BasicResponse(userService.saveUser(user), "OK");
        } catch (Exception e) {
            response = new BasicResponse(e.getMessage(), "FAILED");
        }
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String autorizationHeader = request.getHeader(AUTHORIZATION);
        if(autorizationHeader != null && autorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = autorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("NaTour21SecretKey".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String accesToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 3 * 3600 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", Collections.singletonList(user.getRole()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", refresh_token);
                tokens.put("refreshToken", accesToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                response.setHeader("Error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else
        {
            throw new RuntimeException("Refresh token is missing");
        }
    }


}
