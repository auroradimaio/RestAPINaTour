package com.example.NaTour21.Waypoints.Controller;

import com.example.NaTour21.Waypoints.Entity.Waypoints;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import com.example.NaTour21.Waypoints.Service.WaypointsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor

public class WaypointsController {

    private final WaypointsService waypointsService;


    @GetMapping("/waypoints")
    public ResponseEntity<BasicResponse>getWaypoints(){
        BasicResponse response = new BasicResponse(waypointsService.getWaypoints().toArray(),"Ok");
        return ResponseEntity.ok().body(response);
    }




    @PostMapping("/insert/waypoints")
    public ResponseEntity<BasicResponse>saveWaypoints(@RequestBody Waypoints waypoints){
        BasicResponse response = null;

        try {
            response = new BasicResponse(waypointsService.saveWaypoints(waypoints),"Ok");
        }catch (Exception e){
            response = new BasicResponse(e.getMessage(),"FAILED");
        }
        return ResponseEntity.ok().body(response);
    }

}
