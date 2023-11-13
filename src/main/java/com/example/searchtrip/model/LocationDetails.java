package com.example.searchtrip.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;


@Data
public class LocationDetails {


    @JsonAlias("StopLocation")
    private Location[] stopLocation;

    @JsonAlias("name")
    private String name;

    @JsonAlias("extId")
    private String extId;

    @JsonAlias("lat")
    private String lat;

    @JsonAlias("lon")
    private String lon;

    @JsonAlias("time")
    private String time;

    @JsonAlias("date")
    private String date;

    @JsonAlias("stops")
    private Location[] stops;

    public LocationDetails(Location location) {
        this.name = location.getName();
        this.extId = location.getExtId();
        this.lat = location.getStops();
        this.lon = location.getLon();
        this.time = location.getTime();
        this.date = location.getDate();
        this.stops = new Location[]{location};
    }


}