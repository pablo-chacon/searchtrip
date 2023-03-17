package com.example.searchtrip.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/search/")
public class TripController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("route")
    @ResponseBody
    public String getRoute(@RequestParam("originId") String originId, @RequestParam("destId") String destId) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId) //"740000001"
                .append("&destId=").append(destId) //"740000003"
                .append("&passlist=").append("true")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);

        return response.getBody();
    }

    //Find origin by name.
    @GetMapping("fromOrigin")
    @ResponseBody
    public String getOrigin(@RequestParam("input") String input) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(input)
                .append("&format=").append("json")
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }

    // Find destination by name.
    @GetMapping("toDestination")
    @ResponseBody
    public String getRoute(@RequestParam("input") String input) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/location.name?");
        builder
                .append("input=").append(input)
                .append("&format=").append("json")
                .append("&accessId=").append("YOUR-API-KEY");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }

}
