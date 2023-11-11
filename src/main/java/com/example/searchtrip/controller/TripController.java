package com.example.searchtrip.controller;

import com.example.searchtrip.model.Complaint;
import com.example.searchtrip.model.GeoIP;
import com.example.searchtrip.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class TripController {


    @Autowired
    RestTemplate restTemplate;


    public boolean isFavorite;
    public Complaint complaint;
    public Location location;
    public GeoIP geoIP;
    public HashMap<String, Location> locations = new HashMap<>();
    public List<String> favorites = new ArrayList<>();
    public List<Complaint> complaints = new ArrayList<>();



    @GetMapping
    public String getRoutes() {
        return "Search history:" + locations.toString();
    }

    @GetMapping("favorites")
    public List<String> getFavorites() {
        return favorites;
    }

    @PutMapping("updateFavorite/{id}")
    public void updateFavorite(@RequestParam("id") String id, @PathVariable String name) {
        favorites.remove(id);
        favorites.add(Integer.parseInt(id), getRoute(locations.get(name).extId, locations.get(name).extId));
        System.out.println("Favorite updated.");

    }
    @GetMapping("addfavorite")
    public void addFavorite() {

        String route = getRoute(location.extId, location.extId);
        favorites.add(route);
        System.out.println("Favorite added.");
    }

    @DeleteMapping("favorite/{id}")
    public void removeFavorite(@RequestParam("id") String id) {
        favorites.remove(id);
        System.out.println("Favorite removed.");
    }

    @GetMapping("favorite/{id}")
    public String getFavorite(@RequestParam("id") String id) {
        String favorite = favorites.get(Integer.parseInt(id));
        ResponseEntity<String> response = restTemplate
                .getForEntity(favorite.toString(), String.class);

        return response.getBody();

    }

    @GetMapping("complaints")
    public List<Complaint> getComplaints() {
        System.out.println("Your complaints: ");
        return complaints;
    }

    @PostMapping("complaint/{topic}/{description}")
    public List<Complaint> addComplaint(@RequestParam("topic") String topic,
                                     @RequestParam("description") String description) {
        complaint.setHead(topic);
        complaint.setBody(description);
        complaints.add(complaint);

        return complaints;
    }

    @GetMapping("route/{originId}/{destination}")
    @ResponseBody
    public String getRoute(@RequestParam("originId") String originId, @RequestParam("destId") String destId) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId) //"740000001"
                .append("&destId=").append(destId) //"740000003"
                .append("&passlist=").append("true")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    @GetMapping("route")
    @ResponseBody
    public String getRouteFromCurrentLocation(@RequestParam("destId") String destId) {
        String originId = geoIP.getLatitude() + "," + geoIP.getLongitude();
        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId) //"740000001"
                .append("&destId=").append(destId) //"740000003"
                .append("&passlist=").append("true")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    //Find origin by name. Free search utilizing "res-robot".
    @PostMapping("fromOrigin/{origin}")
    @GetMapping("fromOrigin")
    @ResponseBody
    public Location getOrigin(@RequestParam("origin") String origin) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(origin)
                .append("&format=").append("json")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<Location> response = restTemplate
                .getForEntity(builder.toString(), Location.class);

        locations.put(origin, response.getBody());

        return response.getBody();
    }

    // Find destination by name.
    @PostMapping("toDestination/{destination}")
    @GetMapping("toDestination")
    @ResponseBody
    public Location getDestination(@RequestParam("destination") String destination) {


        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(destination)
                .append("&format=").append("json")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<Location> response = restTemplate
                .getForEntity(builder.toString(), Location.class);

        locations.put(destination, response.getBody());

        return response.getBody();
    }

}

