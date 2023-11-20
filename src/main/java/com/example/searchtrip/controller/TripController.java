package com.example.searchtrip.controller;

import com.example.searchtrip.service.TripService;
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
    @Autowired
    TripService tripService;
    public HashMap<String, String> routes = new HashMap<>();
    public List<String> favorites = new ArrayList<>();
    public List<String> complaints = new ArrayList<>();


    @GetMapping
    public String getRoutes() {
        return "Search history:" + routes;
    }

    @GetMapping("get/favorites")
    public List<String> getFavorites() {
        System.out.println("Your favorites: ");
        return favorites;
    }

    @GetMapping("get/complaints")
    public List<String> getComplaints() {
        System.out.println("Your complaints: ");
        return complaints;
    }

    /**
     * Get route from origin to destination.
     * Add origin and destination to routes.
     * @param originId string EXTID or lat + lon is valid.
     * @param destId string EXTID or lat + lon is valid.
     * @return
     */
    @GetMapping("route")
    @ResponseBody
    public String getRoute(@RequestParam("originId") String originId, @RequestParam("destId") String destId) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId) //"740000001"
                .append("&destId=").append(destId) //"740000003"
                .append("&passlist=").append("true")
                .append("&originwalk=").append("1,0,1000,75")
                .append("destwalk=").append("1,0,1000,75")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        routes.put(originId + " to " + destId, builder.toString());

        return response.getBody();
    }

    /**
     * Get origin, location of departure.
     * @param input
     * @return response stop/station/location by name.
     */
    @GetMapping("fromOrigin")
    @ResponseBody
    public String getOrigin(@RequestParam("input") String input) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(input)
                .append("&format=").append("json")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }

    /**
     * Get current geolocation by IP.
     * @return response body.
     */
    @GetMapping("currentlocation")
    private ResponseEntity<String> getLocation() {

        StringBuilder builder = new StringBuilder("https://api.geoapify.com/v1/ipinfo?" );
        builder
            .append("&apiKey=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * Get destination, location of arrival.
     * @param input
     * @return response a stop/station/location by name.
     */
    @GetMapping("toDestination")
    @ResponseBody
    public String getDest(@RequestParam("input") String input) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(input)
                .append("&format=").append("json")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    /**
     * Get nearby stops, by geolocation.
     * @param latitude
     * @param longitude
     * @return response nearby stops by geolocation.
     */
    @GetMapping("get/nearbystops")
    @ResponseBody
    public String getNearbyStops(@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.nearbystops?");
        builder
                .append("originCoordLat=").append(latitude)
                .append("&originCoordLong=").append(longitude)
                .append("&format=").append("json")
                .append("&maxNo=").append("10")
                .append("&maxDist=").append("500")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    /**
     * Get favorite by id.
     * Request param id is the index of the favorite.
     * @pathvariable id.
     * @return response favorite response.
     */
    @GetMapping("get/favorite/{id}")
    @ResponseBody
    public String getFavorite(@PathVariable int id) {

        String favorite = favorites.get(id);
        ResponseEntity<String> response = restTemplate
                .getForEntity(favorite, String.class);

        return response.getBody();
    }

    /**
     * @param originId string EXTID or lat + lon is valid.
     * @param destId string EXTID or lat + lon is valid
     * Adds favorite uri t list.
     */
    @PostMapping("add/favorite")
    @GetMapping("add/favorite")
    @ResponseBody
    public void addFavorite(@RequestParam("originId") String originId, @RequestParam("destId") String destId) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId)
                .append("&destId=").append(destId)
                .append("&passlist=").append("true")
                .append("&accessId=").append("YOUR-API-KEY");

        favorites.add(builder.toString());

        System.out.println("Favorite added.");
    }

    /**
     * @param originId
     * @param id
     * Update favorite from search history.
     */
    @PutMapping("update/favorite/{id}")
    @ResponseBody
    public void updateFavorite(@RequestParam("originId") String originId, @PathVariable String id) {

        favorites.remove(id);
        favorites.add(Integer.parseInt(id), String.valueOf(routes.containsValue(originId)));

        System.out.println("Favorite updated.");
    }

    /**
     * @param id
     * Remove favorite from list.
     */
    @DeleteMapping("remove/favorite{id}")
    @ResponseBody
    public void removeFavorite(@PathVariable("id") String id) {

        favorites.remove(id);

        System.out.println("Favorite removed.");
    }

    /**
     * @param topic
     * @param description
     * Add complaint to list.
     */
    @PostMapping("add/complaint")
    @ResponseBody
    public List<String> addComplaint(@RequestParam("topic") String topic, @RequestParam("description") String description) {
        complaints.add("topic: " + topic + " description: " + description);

        return complaints;
    }

}

