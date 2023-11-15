package com.example.searchtrip.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeoIpDetails {

    @JsonAlias("city")
    private String city;
    @JsonProperty("location")
    public String location;
    @JsonAlias("longitude")
    private String lon;
    @JsonAlias("latitude")
    private String lat;


    public GeoIpDetails(GeoIP geoIP) {
        this.location = geoIP.getLocation();
    }

}



