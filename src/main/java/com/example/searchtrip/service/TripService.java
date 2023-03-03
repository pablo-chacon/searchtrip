package com.example.searchtrip.service;

import com.example.searchtrip.model.FindTrip;
import com.example.searchtrip.model.TripDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TripService {


    private RestTemplate restTemplate;

    public TripService() {
        this.restTemplate = new RestTemplate();
               //
    }
    public FindTrip getRestTemplate(String origin, String dest) {
        FindTrip findTrip = new FindTrip();
        return restTemplate.getForObject("https://api.resrobot.se/v2.1/trip?", FindTrip.class);
    }

    public TripDetails[] newTrip(RestTemplate restTemplate) {
        FindTrip findTrip = new FindTrip();
        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(findTrip.getOriginId()) //"740000001"
                .append("&destId=").append(findTrip.getDestId())
                .append("&passlist=").append(findTrip.getStops())
                .append("&accessId=").append("apiKey");

        TripDetails[] details = restTemplate
                .getForObject(builder.toString(), TripDetails[].class);
        return details;
    }
}
