package com.example.NaTour21.Waypoints.Repository;

import com.example.NaTour21.Waypoints.Entity.Waypoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WaypointsRepository extends JpaRepository<Waypoints, Long> {

   // Waypoints findWaypointsByWay_id(Long way_id);
   // List<Waypoints> findWaypointsByWay_id(Integer way_id);
    List<Waypoints>findAll();



}
