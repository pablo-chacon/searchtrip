package com.example.searchtrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

// Work in progress.
@Controller
@RequestMapping("/geo/")
public class GeoController {
        //https://api.geoapify.com/v1/geocode/search?REQUEST_PARAMS

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("location")
    @ResponseBody
    private String getLocation(@RequestParam("text") String name) throws UnknownHostException {

        StringBuilder builder = new StringBuilder("https://api.geoapify.com/v1/geocode/search?" );
        builder
                .append("text=").append(name)
                .append("&country=").append("sweden")
                .append("&format=").append("json")
                .append("&apiKey=").append("Api_KEY");


        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }




}

