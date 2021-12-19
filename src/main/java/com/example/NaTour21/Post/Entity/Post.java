package com.example.NaTour21.Post.Entity;

import com.example.NaTour21.Waypoints.Entity.Waypoints;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"post\"")

public class Post {
	
	private String title;
	private String description;
	private int review;
	private String minutes;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial",name="id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "post")
	@JsonManagedReference
	private Waypoints way;
	//change


}
