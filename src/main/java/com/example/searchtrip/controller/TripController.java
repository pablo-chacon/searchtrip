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

    public Complaint complaint;
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

    @GetMapping("complaints")
    public List<Complaint> getComplaints() {
        System.out.println("Your complaints: ");
        return complaints;
    }

    /**
     * Get route from origin to destination.
     *
     * @param origin
     * @param destination
     * @return
     */
    @PostMapping("route/{origin}/{destination}")
    @GetMapping("route/{originId}/{destination}")
    @ResponseBody
    public String getRoute(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(locations.get(origin).extId)
                .append("&destId=").append(locations.get(destination).extId)
                .append("&passlist=").append("true")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    /**
     * Get route from current location to destination.
     * @param destination
     * @return response body.
     */
    @PostMapping("route/location/{destId}")
    @GetMapping("route/location/{destId}")
    @ResponseBody
    public String getRouteFromCurrentLocation(@RequestParam("destId") String destination) {

        String originId = geoIP.getLatitude() + "," + geoIP.getLongitude();

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId)
                .append("&destId=").append(locations.get(destination).extId)
                .append("&passlist=").append("true")
                .append("originWalk=").append("1,0,5000,50")
                .append("destwalk=").append("1,0,5000,50")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    /**
     * Get route from origin to current location.
     * @param origin
     * @return
     */
    @PostMapping("fromOrigin/{origin}")
    @GetMapping("route/{origin}")
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

    /**
     * @param destination
     * @param origin
     * @return
     */
    @PostMapping("toDestination/{destination}")
    @GetMapping("route/{destination}")
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

    /**
     * Get nearby stops.
     * @return response body.
     */
    @PostMapping("nearbystops")
    @GetMapping("nearbystops")
    @ResponseBody
    public String getNearbyStops() {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.nearbystops?");
        builder
                .append("originCoordLat=").append(geoIP.getLatitude())
                .append("&originCoordLong=").append(geoIP.getLongitude())
                .append("&format=").append("json")
                .append("&maxNo=").append("10")
                .append("&maxDist=").append("500")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    /**
     * Get current location.
     * @return response body.
     */
    @PostMapping("currentlocation")
    @GetMapping("currentlocation")
    @ResponseBody
    private String getLocation(@RequestParam("text") String name) {

        StringBuilder builder = new StringBuilder("https://api.geoapify.com/v1/geocode/search?" );
        builder
                .append("text=").append(name)
                .append("&country=").append("sweden")
                .append("&format=").append("json")
                .append("&apiKey=").append("Api_KEY");


        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    /**
     * Get distance from current location to destination.
     * @param mode of transportation. drive/walk/bicycle
     * @param destination
     * @return response body.
     */
    @PostMapping("distance/{destination}/{mode}")
    @GetMapping("distance/{destination}/{mode}")
    @ResponseBody
    public String getDistance(@RequestParam("mode") String mode, @RequestParam("destination") String destination) {

        StringBuilder builder = new StringBuilder("https://api.geoapify.com/v1/routing?" );
            builder
                .append("waypoints=").append(geoIP.getLatitude() + "." + geoIP.getLongitude())
                .append("." + locations.get(destination).getLat() + "." + locations.get(destination).getLon())
                .append("&mode=").append(mode)
                .append("&apiKey=").append("YOUR-API-KEY");
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
    public String getFavorite(@PathVariable String id) {

        String favorite = favorites.get(Integer.parseInt(id));
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
                .append("&originId=").append(origin.lat + "." + origin.lon)
                .append("&destId=").append(destination.lat + "." + destination.lon)
                .append("&passlist=").append("true")
                .append("&accessId=").append("YOUR-API-KEY");

        favorites.add(builder.toString());
        System.out.println("Favorite added.");
    }

    /**
     * Update favorite.
     * @param id
     * @param name
     */
    @PutMapping("favorite/update/{id}")
    @ResponseBody
    public void updateFavorite(@RequestParam("id") String id, @PathVariable String name) {

        favorites.remove(id);
        favorites.add(Integer.parseInt(id), getRoute(locations.get(name).extId, locations.get(name).extId));
        System.out.println("Favorite updated.");
    }

    @DeleteMapping("favorite/remove/{id}")
    @ResponseBody
    public void removeFavorite(@RequestParam("id") String id) {

        favorites.remove(id);
        System.out.println("Favorite removed.");
    }

    @PostMapping("complaint/add/{topic}/{description}")
    @ResponseBody
    public List<Complaint> addComplaint(@RequestParam("topic") String topic,
                                        @RequestParam("description") String description) {
        complaint.setHead(topic);
        complaint.setBody(description);
        complaints.add(complaint);

        return complaints;
    }
}

