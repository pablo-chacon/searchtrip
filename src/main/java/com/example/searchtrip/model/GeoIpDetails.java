package com.example.searchtrip.model;

import com.example.searchtrip.model.GeoIP;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class GeoIpDetails {

    @JsonAlias("name")
    public GeoIP[] name;

    @JsonAlias("lon")
    private String lon;
    @JsonAlias("lat")
    private String lat;
    @JsonAlias("city")
    private String city;


    public GeoIpDetails(GeoIP geoIP) {
        this.lon = geoIP.getLongitude();
        this.lat = geoIP.getLatitude();
    }

}
