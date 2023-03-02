package com.example.searchtrip.controller;

import com.example.searchtrip.model.Geo;
import com.example.searchtrip.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.Inet4Address;
import java.net.UnknownHostException;


@Controller
@RequestMapping("/geo")
public class GeoController {



    @GetMapping("/data")
    @ResponseBody
    private String getGeoData() throws UnknownHostException {
        String ipAddress = "8.8.8.8";//Inet4Address.getLocalHost().getHostAddress();
        String uri = "http://api.ipstack.com/" + ipAddress +
                "?access_key=d7cda786b503c77ab5ac4dfd377cb0a9";
        RestTemplate restTemplate = new RestTemplate();


        return restTemplate.getForObject(uri, String.class);
    }

}

