package com.example.NaTour21.Post.Service;

import com.example.NaTour21.Waypoints.Entity.Waypoints;
import com.example.NaTour21.Waypoints.Repository.WaypointsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.NaTour21.Post.Entity.Post;
import com.example.NaTour21.Post.Repository.PostRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class PostService {
	
	private final PostRepository postRepository;
	private final WaypointsRepository waypointsRepository;


	
	
	public List<Post> getPosts(){
		//return postRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		//return postRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		return postRepository.findAll();
	}

	public Post getLastPost(){
		return postRepository.findFirstByOrderByIdDesc();
	}

	public Post savePost(Post post) {
		{
			return postRepository.save(post);

		}

	}

	//public Post savePostandWay(Post post, Waypoints way){

		//post.setWay(way);
		//return postRepository.save(post);
	//}




	public Post updatePost(Integer id,String difficulty,String minutes){
		Post post = postRepository.findBy(id);

			post.setDifficulty(difficulty);
			post.setMinutes(minutes);


		return postRepository.save(post);
	}



}
