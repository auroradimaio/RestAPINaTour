package com.example.NaTour21.Review.Entity;
import com.example.NaTour21.Post.Entity.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"review\"")

public class Review {

    private String description;
    private double value;
    private Integer id_post;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial",name="id")
    private Long id;





    /*@ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH},optional = false)
    @JoinColumn(name="id_post",nullable = false)
    @JsonBackReference
    private Post post;*/

}
