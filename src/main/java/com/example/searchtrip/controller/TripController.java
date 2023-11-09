package com.example.searchtrip.controller;
/*
import com.example.searchtrip.model.Location;
import com.example.searchtrip.model.SearchRoute;
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
    public SearchRoute searchRoute;
    private List<SearchRoute> routes = new ArrayList<>();
    private List<String> favorites = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<SearchRoute>> getRoutes() {
        System.out.println(routes);
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/favorites")
    public void getFavorites() {
        System.out.println(favorites);
    }

    //@PostMapping("/route/{originId}/{destinationId}")
    @GetMapping("/addFavorite")
    @ResponseBody
    public void addFavorite() {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(searchRoute.getOriginId())
                .append("&destId=").append(searchRoute.getDestinationId())
                .append("&passlist=").append("true")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        String favoriteEndpoint = builder.toString();
        favorites.add(favoriteEndpoint);
        System.out.println(favorites);
    }

    @GetMapping("/refresh/{id}")
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

    //Find origin by name. Free search utilizing "res-robot".
    @PostMapping("fromOrigin/{name}")
    @GetMapping("fromOrigin")
    @ResponseBody
    public String getOrigin(@RequestParam("name") @PathVariable String input) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(input)
                .append("&format=").append("json")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }

    // Find destination by name.
    @PostMapping("toDestination/{name}")
    @GetMapping("toDestination")
    @ResponseBody
    public String getRoute(@RequestParam("input") String input) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(input)
                .append("&format=").append("json")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }

    @GetMapping("/search/")
    @ResponseBody
    public String searchTrip() {


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


}*/

import com.example.searchtrip.model.Location;
import com.example.searchtrip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/api/*")
public class TripController {


    @Autowired
    TripService tripService;
    @Autowired
    private RestTemplate restTemplate;

    HashMap<String, String> searchHistory = new HashMap<>();
    @GetMapping("/")
    public String getRoutes() {
        System.out.println("Search history:");
        return String.valueOf(searchHistory);
    }

    @GetMapping("/favorites")
    public List<String> getFavorites() {
        System.out.println("Favorites:");
        return tripService.getAllFavorites();
    }

    @GetMapping("/route/{origin}/{destination}")
    @ResponseBody
    public String getRoute(@RequestParam("/origin") Location origin, @RequestParam("/destination") Location destination) {


        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(getOrigin(origin.getExtId())) //Stockholm Central : "740000001"
                .append("&destId=").append(getDestination(destination.getExtId())) //Malmö Central : "740000003"
                .append("&passlist=").append("true")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);
        searchHistory.put(origin.getName(), builder.toString());
        return response.getBody();
    }

    //Find origin by name. Free search utilizing "res-robot".
    @PostMapping("/origin/{name}")
    @GetMapping("/origin")
    @ResponseBody
    public String getOrigin(@RequestParam("name") String name) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(name)
                .append("&format=").append("json")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    // Find destination by name.
    @PostMapping("/destination/{name}")
    @GetMapping("/destination")
    @ResponseBody
    public String getDestination(@RequestParam("name") String name) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(name)
                .append("&format=").append("json")
                .append("&accessId=").append(" 7a44df73-9725-4578-bed3-458c8586bcac ");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);
        //tripService.searchDestination("destination", response.getBody());

        return response.getBody();
    }

    @PutMapping("/updateFavorite/{id}")
    public void updateFavorite(@RequestParam("id") String id, @RequestParam("name") String name) {

        tripService.updateFavorite(id, String.valueOf(searchHistory.containsKey(name)));
    }
    @PostMapping("/favorite/{name}")
    public String addFavorite(@RequestParam("name") String name) {

        return tripService.addFavorite(String.valueOf(searchHistory.containsKey(name))).toString();
    }
    @PostMapping("/complaint")
    public String addComplaint(@RequestParam("topic") String topic, @RequestParam("description") String description) {

        return tripService.addComplaint(topic, description);
    }

    @GetMapping("/complaints")
    public List<String> getComplaints() {
        return tripService.getComplaints();
    }

}

