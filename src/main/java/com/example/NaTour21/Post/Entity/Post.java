package com.example.NaTour21.Post.Entity;

import com.example.NaTour21.Review.Entity.Review;
import com.example.NaTour21.Waypoints.Entity.Waypoints;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"post\"")

public class Post {
	
	private String title;
	private String description;
	private String minutes;
    private String difficulty;
    private String startpoint;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial",name="id")

	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "post")

	@JsonManagedReference
	private Waypoints way;

	/*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "post")
	@JsonManagedReference
	private List<Review> rev;*/


}
