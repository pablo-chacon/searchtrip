package com.example.searchtrip.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;


@Data
public class GeoIpDetails {


    @JsonAlias("location")
    private GeoIP[] location;
    @JsonAlias("city")
    private String city;
    @JsonAlias("longitude")
    private String lon;
    @JsonAlias("latitude")
    private String lat;


    public GeoIpDetails(GeoIP geoIP) {
        this.lon = geoIP.getLongitude();
        this.lat = geoIP.getLatitude();
    }

}



