package com.example.searchtrip.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TripService {


    private RestTemplate restTemplate;

    public TripService() {
        this.restTemplate = new RestTemplate();
    }



}