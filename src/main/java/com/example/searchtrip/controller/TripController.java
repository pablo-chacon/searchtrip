package com.example.searchtrip.controller;

import com.example.searchtrip.model.Complaint;
import com.example.searchtrip.model.GeoIP;
import com.example.searchtrip.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;
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
     * @param originId
     * @param destId
     * @return
     */
    @GetMapping("route/{originId}/{destination}")
    @ResponseBody
    public String getRoute(@RequestParam("originId") String originId, @RequestParam("destId") String destId) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId)
                .append("&destId=").append(destId)
                .append("&passlist=").append("true")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    /**
     * Get route from current location to destination.
     * @param destId
     * @return response body.
     */
    @GetMapping("route/here/{destId}")
    @ResponseBody
    public String getRouteFromCurrentLocation(@RequestParam("destId") String destId) {

        String originId = geoIP.getLatitude() + "," + geoIP.getLongitude();

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId)
                .append("&destId=").append(destId)
                .append("&passlist=").append("true")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    /**
     *
     * @param origin
     * @return
     */
    @PostMapping("fromOrigin/{origin}")
    @GetMapping("fromOrigin/{origin}")
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
     *
     * @param destination
     * @return
     */
    @PostMapping("toDestination/{destination}")
    @GetMapping("toDestination/{destination}")
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
     * Get current location.
     * @return response body.
     */
    @GetMapping("location")
    @ResponseBody
    private String getLocation(@RequestParam("text") String name) throws UnknownHostException {

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
     * @param mode
     * @param destination
     * @return response body.
     */
    @GetMapping("distance/{destination}/{mode}")
    public String getDistance(@RequestParam("mode") String mode, @RequestParam("destination") String destination) {
        // 50.96209827745463%2C4.414458883409225%7C50.429137079078345%2C5.00088081232559
        StringBuilder builder = new StringBuilder("https://api.geoapify.com/v1/routing?" );
        builder
                .append("waypoints=").append(geoIP.getLatitude() + "." + geoIP.getLongitude() +
                        "." + locations.get(destination).getLat() + "." + locations.get(destination).getLon())
                .append("&mode=").append(mode)
                .append("&apiKey=").append("YOUR-API-KEY");
        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);
        return response.getBody();
    }

    @GetMapping("addfavorite/{originId}/{destId}")
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

    @PutMapping("updateFavorite/{id}")
    public void updateFavorite(@RequestParam("id") String id, @PathVariable String name) {
        favorites.remove(id);
        favorites.add(Integer.parseInt(id), getRoute(locations.get(name).extId, locations.get(name).extId));
        System.out.println("Favorite updated.");

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
                .getForEntity(favorite, String.class);

        return response.getBody();
    }

    @PostMapping("complaint/{topic}/{description}")
    public List<Complaint> addComplaint(@RequestParam("topic") String topic,
                                        @RequestParam("description") String description) {
        complaint.setHead(topic);
        complaint.setBody(description);
        complaints.add(complaint);

        return complaints;
    }

}

