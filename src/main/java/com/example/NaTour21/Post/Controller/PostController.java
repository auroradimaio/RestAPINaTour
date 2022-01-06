package com.example.NaTour21.Post.Controller;

import com.example.NaTour21.Post.Entity.Post;
import com.example.NaTour21.Post.Service.PostService;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import com.example.NaTour21.Waypoints.Entity.Waypoints;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;

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


	 @GetMapping("/post/update")
	public ResponseEntity<BasicResponse>updatePost(@Param("difficulty")String difficulty,@Param("minutes")String minutes,@Param("id")int id){
		 BasicResponse response = new BasicResponse(postService.updatePost(id,difficulty,minutes),"OK");
		 return ResponseEntity.ok().body(response);

	 }

	 @GetMapping("/postlast")
	public ResponseEntity<BasicResponse>getLast(){
		 BasicResponse response = new BasicResponse((postService.getLastPost()),"OK");
		 return ResponseEntity.ok().body(response);
	 }

}
