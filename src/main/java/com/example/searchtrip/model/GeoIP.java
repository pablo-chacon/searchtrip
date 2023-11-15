package com.example.searchtrip.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeoIP {

    @Id
    @GeneratedValue
    private String ipAddress;
    private String city;
    private String country;
    private String location;
    private String longitude;
    private String latitude;


}
