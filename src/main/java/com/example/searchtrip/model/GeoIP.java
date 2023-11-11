package com.example.searchtrip.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoIP {

    private String ipAddress;
    private String cityName;
    private String latitude;
    private String longitude;


}
