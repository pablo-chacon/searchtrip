package com.example.searchtrip.controller;
/*
import com.example.searchtrip.model.GeoIP;
import com.example.searchtrip.model.Location;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/*")
public class GeoIPController {


    public RestTemplate restTemplate;
    public GeoIP geoIP = new GeoIP();

    /**
     * Get current location.
     *
     * @return response body.
     */
/*
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@PostMapping("currentlocation")
    @GetMapping("currentlocation")
    private Class<? extends ResponseEntity> getLocation(@RequestParam("text") String name) {

        StringBuilder builder = new StringBuilder("https://api.geoapify.com/v1/geocode/search?" );
        builder
                .append("text=").append(name)
                .append("&country=").append("sweden")
                .append("&format=").append("json")
                .append("&apiKey=").append("00dee41792a54aee8cbb5a6f145b3d0d");


        ResponseEntity<GeoIP> response = restTemplate
                .getForEntity(builder.toString(), GeoIP.class);

        return response.getClass();
    }

    /**
     * Get distance from current location to destination.
     * @param mode of transportation. drive/walk/bicycle
     * @param destination
     * @return response body.
     */
/*
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@PostMapping("distance/{destination}/{mode}")
    @GetMapping("distance/{destination}/{mode}")
    @ResponseBody
    public String getDistance(@RequestParam("mode") String mode, @RequestParam("destination") Location destination) {

        StringBuilder builder = new StringBuilder("https://api.geoapify.com/v1/routing?" );
        builder
                .append("waypoints=").append(geoIP.getLatitude() + "." + geoIP.getLongitude())
                .append("." + destination.getLat() + "." + destination.getLon())
                .append("&mode=").append(mode)
                .append("&apiKey=").append("00dee41792a54aee8cbb5a6f145b3d0d");
        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);
        return response.getBody().toString();
    }
}

 */
