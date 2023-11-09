package com.example.searchtrip.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class TripService {


    private List<String> favoriteList;
    private List<String> complaints;


    public TripService() {
        this.favoriteList = new ArrayList<>();
        this.complaints = new ArrayList<>();
    }


    public List<String> addFavorite(String name) {
        favoriteList.add(String.valueOf(new HashMap<>()));
        return favoriteList;
    }

    /**
     * Removes favorite list from the favoriteLists HashMap.
     * @param favorite The name of the favorite list to be removed.
     */
    public void removeFavorite(String favorite) {
        favoriteList.remove(favorite);
    }

    /**
     * Adds a favorite to the favorite list.
     * @param favorite The name of the favorite list to add the favorite to.
     * @param newFavorite The favorite to add to the favorite list.
     */
    public void updateFavoriteList(String favorite, String newFavorite) {
        favoriteList.remove(favorite);
        favoriteList.add(newFavorite);
    }

    public List<String> getAllFavorites() {

        return favoriteList;
    }

    public String addComplaint(String topic, String description) {
        complaints.add(topic + "\n" + description);
        return "Complaint registered.";
    }
}

