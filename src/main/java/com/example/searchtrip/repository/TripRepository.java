package com.example.searchtrip.repository;


import com.example.searchtrip.model.FindTrip;
import com.example.searchtrip.model.TripDetails;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class TripRepository {


    TripDetails tripDetails;
    public List<HashMap<String, String>> tripDetailsList = new ArrayList<>();


    public List<HashMap<String, String>> addNewLocation(FindTrip findTrip) {
        tripDetailsList.add(tripDetails.locations(findTrip));
        return tripDetailsList;
    }

    public List<HashMap<String, String>> updateLocation(FindTrip findTrip) {
        tripDetails = new TripDetails(findTrip);
        tripDetailsList.remove(tripDetails.locations(findTrip));
        return tripDetailsList;
    }



}
