package com.example.searchtrip.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class TripService {


    private RestTemplate restTemplate;
    List<String> origin = Arrays.asList("origin");
    public TripService() {
        this.restTemplate = new RestTemplate();
    }

    public List<String> getLocation() {
        String geo = restTemplate.getForObject("http://127.0.0.1:9999/api/currentlocation/", String.class);
        return Arrays.asList(geo.split(","));
    }

    public String getOrigin(String origin) {
        return restTemplate.getForObject("http://127.0.0.1:9999/api/from?" + origin, String.class);
    }

    public String getDestination(String destination) {
        return restTemplate.getForObject("http://127.0.0.1:9999/api/to/" + destination, String.class);
    }



}


