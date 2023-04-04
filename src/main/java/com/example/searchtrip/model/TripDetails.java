package com.example.searchtrip.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;


@Data
public class TripDetails  {

    @JsonAlias("StopLocation")
    private FindTrip[] stopLocation;

    @JsonAlias("name")
    private String name;

    @JsonAlias("extId")
    private String extId;

    @JsonAlias("lat")
    private String lat;

    @JsonAlias("lon")
    private String lon;

    public TripDetails(FindTrip findTrip) {
        this.name = findTrip.getName();
        this.extId = findTrip.getExtId();
        this.lat = findTrip.getStops();
        this.lon = findTrip.getLon();
    }

}