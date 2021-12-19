package com.example.NaTour21.Post.Repository;
import com.example.NaTour21.Post.Entity.Post;
import com.example.NaTour21.User.Entity.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
	EntityManager entityManager = null;

	//Optional<Post> findById(Long id, Sort sort);
	//public List<Post> findAllByOrderByIdDesc();

	//@EntityGraph(value = "Subject.allJoins", type = EntityGraph.EntityGraphType.FETCH)
	//@Query(value="SELECT p.description,p.review,p.title,p.minutes, p.id, w.lat1,w.lat2,w.lon1,w.lon2 FROM post p "+
	//		"join waypoints w on p.id = w.id", nativeQuery = true)
	//@Fetch(FetchMode.SUBSELECT)
		//@Query(value = "SELECT all FROM post INNER JOIN FETCH waypoints")
	//@EntityGraph(attributePaths = {"post","waypoints"})
	//@Query(nativeQuery = true,value="SELECT * FROM post WHERE id = 1")
	//String jpql = "select p from post p where p.id in :waypoints";
	//@Query(value = "SELECT DISTINCT p from post p INNER JOIN  p.waypoints",nativeQuery = true)

	/*List<Post> posts = entityManager.createQuery("""select distinct p from Post p inner join fetch p.waypoints where p.id between :minId and :maxId""",Post.class)
			.setParameter("minId",1L)
			.setParameter("maxId",50L)
			.setHint(QueryHints.PASS_DISTINCT_THROUGH,false)
			.getResultList();*/
	List<Post>findAll();
}
