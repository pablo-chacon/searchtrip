package com.example.searchtrip.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class TripDetails {

    @JsonAlias("Trip")
    private FindTrip[] trip;

    @JsonAlias("Origin")
    private String origin;
    @JsonAlias("Destination")
    private String destination;
    @JsonAlias("Stops")
    private String stops;


    public TripDetails(FindTrip findTrip) {
        this.origin = findTrip.getOriginId();
        this.destination = findTrip.getDestId();
        this.stops = findTrip.getStops();
    }

}