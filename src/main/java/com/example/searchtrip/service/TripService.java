package com.example.searchtrip.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;


@Service
public class TripService {


    private final RestTemplate restTemplate;


    List<String> origin = Arrays.asList("origin");

    public TripService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getLocation() {
        //Arrays.asList(geo.split(","));
        return restTemplate.getForEntity("http://127.0.0.1:9999/api/currentlocation/", String.class);
    }

    public String getOrigin(String origin) {
        return restTemplate.getForObject("http://127.0.0.1:9999/api/from?" + origin, String.class);
    }

    public String getDestination(String destination) {
        return restTemplate.getForObject("http://127.0.0.1:9999/api/to/" + destination, String.class);
    }

    public String getRoute(String origin, String destination) {
        return restTemplate.getForObject("http://127.0.0.1:9999/api/route/" + origin + "/" + destination, String.class);
    }

}


