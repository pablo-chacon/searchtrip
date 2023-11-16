package com.example.searchtrip.controller;

import com.example.searchtrip.model.GeoIP;
import com.example.searchtrip.model.Location;
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



    public HashMap<String, Location> routes = new HashMap<>();

    public List<String> favorites = new ArrayList<>();
    public List<String> complaints = new ArrayList<>();


    @GetMapping
    public String getRoutes() {
        return "Search history:" + routes;
    }

    @GetMapping("favorites")
    public List<String> getFavorites() {
        return favorites;
    }

    @GetMapping("complaints")
    public List<String> getComplaints() {
        System.out.println("Your complaints: ");
        return complaints;
    }

    /**
     * Get route from origin to destination.
     *
     * @param originId
     * @param destId
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
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }



    @GetMapping("fromOrigin")
    @ResponseBody
    public String getOrigin(@RequestParam("input") String input) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(input)
                .append("&format=").append("json")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }


    /**
     * Get current location.
     * @return response body.
     */
    @GetMapping("currentlocation")
    private ResponseEntity<String> getLocation() {

        StringBuilder builder = new StringBuilder("https://api.geoapify.com/v1/ipinfo?" );
        builder
            .append("&apiKey=").append("00dee41792a54aee8cbb5a6f145b3d0d");


        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return ResponseEntity.ok(response.getBody());
    }

    /**
     * @param input
     * @return response body.
     */

    @GetMapping("toDestination")
    @ResponseBody
    public String getDest(@RequestParam("input") String input) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(input)
                .append("&format=").append("json")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }

    /**
     * Get nearby stops.
     * @return response body.
     */
    //@PostMapping("nearbystops")
    @GetMapping("nearbystops")
    @ResponseBody
    public String getNearbyStops(@PathVariable("nearbystops") GeoIP geoIP) {

        //geoIP = tripService.getLocation();

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.nearbystops?");
        builder
                .append("originCoordLat=").append(geoIP.getLatitude())
                .append("&originCoordLong=").append(geoIP.getLongitude())
                .append("&format=").append("json")
                .append("&maxNo=").append("10")
                .append("&maxDist=").append("500")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }



    /**
     * Get favorite.
     * @param id
     * @return response body.
     *
     * @param id
     * @return response body.
     */
    @GetMapping("favorite/get/{id}")
    @ResponseBody
    public String getFavorite(@PathVariable int id) {

        String favorite = favorites.get(id);
        ResponseEntity<String> response = restTemplate
                .getForEntity(favorite, String.class);

        return response.getBody();
    }

    @PostMapping("favorite/add/{origin}/{destination}")
    @GetMapping("favorite/add/{originId}/{destId}")
    @ResponseBody
    public void addFavorite(@RequestParam("originId") Location origin, @RequestParam("destId") Location destination) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(origin.getExtId())
                .append("&destId=").append(destination.getExtId())
                .append("&passlist=").append("true")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        favorites.add(builder.toString());
        System.out.println("Favorite added.");
    }

    /*@PutMapping("favorite/update/{id}")
    @ResponseBody
    public void updateFavorite(@RequestParam("id") String id, @PathVariable String name) {

        favorites.remove(id);
        favorites.add(Integer.parseInt(id), getRoute(routes.get(name).extId, routes.get(name).extId));
        System.out.println("Favorite updated.");
    }*/

    @DeleteMapping("favorite/remove/{id}")
    @ResponseBody
    public void removeFavorite(@RequestParam("id") String id) {

        favorites.remove(id);
        System.out.println("Favorite removed.");
    }

    @PostMapping("complaint/add/{topic}/{description}")
    @ResponseBody
    public List<String> addComplaint(@RequestParam("topic") String topic,
                                        @RequestParam("description") String description) {
        complaints.add("topic: " + topic + " description: " + description);

        return complaints;
    }
}

