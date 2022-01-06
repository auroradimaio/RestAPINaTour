package com.example.NaTour21.Review.Repository;

import com.example.NaTour21.Review.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAll();

    @Query(value = "SELECT * FROM review WHERE id_post = ?1",nativeQuery = true)
    List<Review> findAllBy(Integer id_post);

}
