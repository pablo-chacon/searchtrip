package com.example.searchtrip.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@Data
public class TripDetails implements Collection<TripDetails> {

    @JsonAlias("StopLocation")
    private FindTrip[] stopLocation;

    @JsonAlias("name")
    private String name;

    @JsonAlias("extId")
    private String extId;

    @JsonAlias("lat")
    private String lat;

    @JsonAlias("lon")
    private String lon;

    public TripDetails(FindTrip findTrip) {
        this.name = findTrip.getName();
        this.extId = findTrip.getExtId();
        this.lat = findTrip.getStops();
        this.lon = findTrip.getLon();
    }

    public HashMap<String, String> locations(FindTrip findTrip) {
        HashMap<String, String> locationMap = new HashMap<>();
        TripDetails location = new TripDetails(findTrip);
        locationMap.put("name", location.getName());
        locationMap.put("extId", location.getExtId());
        locationMap.put("lat", location.getLat());
        locationMap.put("lon", location.getLon());
        return locationMap;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<TripDetails> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(TripDetails tripDetails) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends TripDetails> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}