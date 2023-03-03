package com.example.searchtrip.repository;


import com.example.searchtrip.model.FindTrip;
import com.example.searchtrip.model.GeoIP;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository {


    //Whats up with the f in findTrip?
    FindTrip findTrip = new FindTrip();
    GeoIP geoIP = new GeoIP();

    List<FindTrip> findNearbyStation(String lon, String lat);
}
