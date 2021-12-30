package com.example.NaTour21.Post.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.NaTour21.Post.Entity.Post;
import com.example.NaTour21.Post.Service.PostService;
import com.example.NaTour21.User.Entity.User;
import com.example.NaTour21.User.Service.UserService;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
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


public class PostController {
	
	private final PostService postService;
	
	 @GetMapping("/posts")
	 public ResponseEntity<BasicResponse>getPosts(){
		 BasicResponse response = new BasicResponse(postService.getPosts().toArray(),"OK");
		 return ResponseEntity.ok().body(response);
	 }



	 @PostMapping("/post/insert")
	public ResponseEntity<BasicResponse>savePost(@RequestBody Post post){
		 BasicResponse response = null;
		 try {
			 response = new BasicResponse(postService.savePost(post), "OK");
		 } catch (Exception e){
			 response = new BasicResponse(e.getMessage(), "FAILED");
		 }
		 return ResponseEntity.ok().body(response);
	 }

}
