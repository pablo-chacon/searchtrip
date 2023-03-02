package com.example.searchtrip.repository;


import com.example.searchtrip.model.FindTrip;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository {

    FindTrip byOriginDestinationName(String originName, String destName);
    List<FindTrip> findNearbyStation(String lon, String lat);
}
