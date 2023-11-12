package com.example.searchtrip.controller;
/*
import com.example.searchtrip.model.GeoIP;
import com.example.searchtrip.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

// Work in progress.
@Controller
@RequestMapping("/geo/")
public class GeoController {
        //https://api.geoapify.com/v1/geocode/search?REQUEST_PARAMS

    @Autowired
    RestTemplate restTemplate = new RestTemplate();
    GeoIP geoIP;
    Location location;

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
     *
     * @param mode
     * @return
     */
/*
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@GetMapping("distance/{mode}")
    public String getDistance(@RequestParam("mode") String mode) {
        // 50.96209827745463%2C4.414458883409225%7C50.429137079078345%2C5.00088081232559
        StringBuilder builder = new StringBuilder("https://api.geoapify.com/v1/routing?" );
        builder
                .append("waypoints=").append(geoIP.getLatitude() + "." + geoIP.getLongitude() +
                        "." + location.getLat() + "." + location.getLon())
                .append("&mode=").append("drive")
                .append("&apiKey=").append("00dee41792a54aee8cbb5a6f145b3d0d");
        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);
        return response.getBody();
    }




}
*/

