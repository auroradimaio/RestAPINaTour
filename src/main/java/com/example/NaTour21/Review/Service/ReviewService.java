package com.example.NaTour21.Review.Service;

import com.example.NaTour21.Review.Entity.Review;
import com.example.NaTour21.Review.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional @Slf4j

public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByIdPost(Integer id_post){
        return reviewRepository.findAllBy(id_post);
    }

    public Review saveReview(Review review){
        return reviewRepository.save(review);
    }


}
