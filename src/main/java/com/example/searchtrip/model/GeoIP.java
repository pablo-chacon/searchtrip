package com.example.searchtrip.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GeoIP {

    private String ipAddress;
    private String city;
    private String latitude;
    private String longitude;


}
