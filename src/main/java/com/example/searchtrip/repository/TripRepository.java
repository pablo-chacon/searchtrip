package com.example.searchtrip.repository;


import com.example.searchtrip.model.FindTrip;
import com.example.searchtrip.model.TripDetails;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

import java.util.List;


@Repository
public class TripRepository {


    TripDetails tripDetails;
    public List<TripDetails> tripDetailsList = new ArrayList<>();



    public List<TripDetails> addNewLocation(TripDetails tripDetails) {
        tripDetailsList.add(tripDetails);
        return tripDetailsList;
    }




}
