package com.example.NaTour21.Post.Repository;

import com.example.NaTour21.Post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {


	List<Post>findAll();

	@Query(value="SELECT * FROM post WHERE id = ?1",nativeQuery = true)
	Post findBy(Integer id);

	Post findFirstByOrderByIdDesc();


}
