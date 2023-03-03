package com.example.searchtrip.model;

import lombok.Data;


@Data
public class FindTrip {

    public String id;
    public String city;
    public String originLat, originLong;
    public String destLat, destLong;
    public String stops;



}
