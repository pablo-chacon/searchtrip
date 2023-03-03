package com.example.searchtrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/search/")
public class TripController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("find")
    public void testing(RestTemplate restTemplate) {
        /*StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(findTrip.getOriginName()) //"740000001"
                .append("&destId=").append(findTrip.getDestName())
                .append("&passlist=").append(findTrip.getStops())
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        TripDetails[] details = restTemplate
                .getForObject(builder.toString(), TripDetails[].class);
        return ResponseEntity.ok();*/

    }
    /*public List<TripDetails> findTrip(RestTemplate restTemplate) {
        TripDetails[] tripDetails = restTemplate.getForObject("https://api.resrobot.se/v2.1/trip?", TripDetails[].class);

        return Arrays.asList(tripDetails);
    }

    @GetMapping("/route/pill")
    public ResponseEntity<FindTrip[]> getGeoCoding(RestTemplate restTemplate, FindTrip findTrip) {
        FindTrip details = restTemplate.getForObject("https://api.resrobot.se/v2.1/trip?", (Class<FindTrip>) FindTrip[].class);
        StringBuilder builder = new StringBuilder();
        builder
                .append("format=").append("json")
                .append("&name").append(findTrip.getOriginName()) //
                .append("&destId=").append(findTrip.getDestName())
                .append("&accessId=").append("7a44df73-9725-4578-bed3-458c8586bcac");

        ResponseEntity<TripDetails>  = restTemplate
                .getForEntity(builder.toString(), TripDetails.class);


        return ResponseEntity.ok(traffic.getBody());
    }*/

    @GetMapping("route")
    @ResponseBody
    public String getTrip(@RequestParam("originId") String originId, @RequestParam("destId") String destId) {

        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append(originId) //"740000001"
                .append("&destId=").append(destId) //"740000003"
                .append("&passlist=").append("true")
                .append("&accessId=").append("yourApiKey");

        ResponseEntity<String> response = restTemplate
                .getForEntity(builder.toString(), String.class);


        return response.getBody();
    }
}
