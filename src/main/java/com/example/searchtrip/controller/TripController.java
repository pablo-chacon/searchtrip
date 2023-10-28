package com.example.searchtrip.controller;


import com.example.searchtrip.model.FindTrip;
import com.example.searchtrip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Controller
@RequestMapping("/search/")
public class TripController {

    @Autowired
    private TripService tripService;
    @Autowired
    RestTemplate restTemplate;

    private List<FindTrip> routes = List.of(
            new FindTrip("1","740000001", "Stockholm city", "18.058151", "59.330136", ""));
    @GetMapping("/")
    public ResponseEntity<List<FindTrip>> getRoutes() {
        System.out.println(routes);
        return ResponseEntity.ok(routes);
    }

    @GetMapping("route")
    @ResponseBody
    public String getRoute(@RequestParam("originId") String originId, @RequestParam("destId") String destId) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId) //
                .append("&destId=").append(destId) //"740000003"
                .append("&passlist=").append("true")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    //Find origin by name. Free search utilizing "res-robot".
    @PostMapping("fromOrigin")
    @GetMapping("fromOrigin/{origin}")
    @ResponseBody
    public String getOrigin(@RequestParam("origin") String origin) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(origin)
                .append("&format=").append("json")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }

    // Find destination by name.
    @PostMapping("toDestination/{destination}")
    @GetMapping("toDestination")
    @ResponseBody
    public String getDestination(@RequestParam("destination") String destination) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(destination)
                .append("&format=").append("json")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }


}
