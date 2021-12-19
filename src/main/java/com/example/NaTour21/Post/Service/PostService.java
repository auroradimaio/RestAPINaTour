package com.example.NaTour21.Post.Service;

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


	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public List<Post> getPosts(){
		//return postRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		//return postRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		return postRepository.findAll();
	}

	public Post savePost(Post post) {
		{
			return postRepository.save(post);
		}

	}

	public List <Post> getPostsandWaypoints() throws HibernateException{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT p.description,p.review,p.title,p.minutes, p.id, w.lat1,w.lat2,w.lon1,w.lon2 FROM post p INNER JOIN p.waypoints w");
		//List<Post> results = query.getResultList();
		List<Post> results = session.createCriteria(Post.class).list();
		if(results!= null && results.size()>0){
			return results;
		}
		return null;
	}


	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

}
