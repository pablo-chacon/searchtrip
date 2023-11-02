package com.example.searchtrip.controller;

import com.example.searchtrip.model.FindTrip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TripController {


    @Autowired
    RestTemplate restTemplate;

    private List<FindTrip> routes = new ArrayList<>();
    private List<String> favorites = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<FindTrip>> getRoutes() {
        System.out.println(routes);
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/favorites")
    public void getFavorites() {
        System.out.println(favorites);
    }

    @PostMapping("/addFavorite/{originId}/{destinationId}")
    @GetMapping("/addFavorite")
    @ResponseBody
    public void addFavorite(@RequestParam("originId") String originId, @RequestParam("destId") String destId) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId)
                .append("&destId=").append(destId)
                .append("&passlist=").append("true")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        String favoriteEndpoint = builder.toString();
        favorites.add(favoriteEndpoint);
        System.out.println(favorites);
    }

    @GetMapping("/fetchFavorite/{id}")
    public String refreshFavorite(@RequestParam String id) {
        ResponseEntity<String> response = restTemplate
                .getForEntity(favorites.get(Integer.valueOf(id)).toString(), String.class);

        return  response.getBody();
    }

    @DeleteMapping("/removeFavorite/{id}")
    public void removeFavorite(@RequestParam int id) {
        favorites.remove(id);
        System.out.println("Favorite removed");
    }

    @PostMapping("/fromOrigin/{origin}")
    @GetMapping("/fromOrigin")
    @ResponseBody
    public ResponseEntity<String> addOrigin(@RequestParam("origin") String origin) {
        FindTrip trip = new FindTrip();
        trip.setName(origin);
        routes.add(trip);
        return ResponseEntity.ok("Origin added successfully");
    }

    @PostMapping("/toDestination/{destination}")
    @GetMapping("/toDestination")
    @ResponseBody
    public ResponseEntity<String> addDestination(@RequestParam("destination") String destination) {
        if (routes.isEmpty()) {
            return ResponseEntity.badRequest().body("Please add origin first");
        }
        FindTrip trip = routes.get(routes.size() - 1);
        trip.setName(destination);
        return ResponseEntity.ok("Destination added successfully");
    }

    @GetMapping("/search/")
    @ResponseBody
    public String searchTrip() {
        if (routes.isEmpty()) {
            return "Add origin and destination";
        }
        FindTrip trip = routes.get(routes.size() - 1);
        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(trip.getExtId())
                .append("&destId=").append(trip.getExtId())
                .append("&passlist=").append("true")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }


}

