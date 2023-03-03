package com.example.searchtrip.model;


import lombok.*;

@Data
public class GeoIP {

    private String ipAddress;
    private String city;
    private String latitude;
    private String longitude;


}
