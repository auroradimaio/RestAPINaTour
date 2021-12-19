package com.example.NaTour21.Waypoints.Entity;


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
@Table(name = "\"waypoints\"")

public class Waypoints {

    private double lat1;
    private double lat2;
    private double lon1;
    private double lon2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial", name="way_id")
    private Long way_id;

   @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH},optional = false)
    @JoinColumn(name = "id", nullable = false)
   @JsonBackReference
    private Post post;

}
