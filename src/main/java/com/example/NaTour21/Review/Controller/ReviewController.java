package com.example.NaTour21.Review.Controller;

import com.example.NaTour21.Post.Entity.Post;
import com.example.NaTour21.Review.Entity.Review;
import com.example.NaTour21.Review.Service.ReviewService;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor


public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<BasicResponse>getReviews(){
        BasicResponse response = new BasicResponse(reviewService.getReviews().toArray(),"OK");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/reviews/id")
    public ResponseEntity<BasicResponse>getReviewsByIdPost(@Param("id_post") Integer id_post){
        BasicResponse response = new BasicResponse(reviewService.getReviewsByIdPost(id_post).toArray(),"OK");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/review/insert")
    public ResponseEntity<BasicResponse>savePost(@RequestBody Review review){
        BasicResponse response = null;
        try {
            response = new BasicResponse(reviewService.saveReview(review), "OK");
        } catch (Exception e){
            response = new BasicResponse(e.getMessage(), "FAILED");
        }
        return ResponseEntity.ok().body(response);
    }

}
