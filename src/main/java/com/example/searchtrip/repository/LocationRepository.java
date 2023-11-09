package com.example.searchtrip.repository;


import com.example.searchtrip.model.LocationDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class LocationRepository {


    LocationDetails locationDetails;
    public List<LocationDetails> locationDetailsList = new ArrayList<>();


    public List<LocationDetails> addNewLocation(LocationDetails locationDetails) {
        locationDetailsList.add(locationDetails);
        return locationDetailsList;
    }


}
