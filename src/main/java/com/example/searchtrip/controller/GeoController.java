package com.example.searchtrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;


@Controller
@RequestMapping("/geo/")
public class GeoController {

    @GetMapping("location")
    @ResponseBody
    private String getLocation() throws UnknownHostException {
        String ipAddress = "ipAddress";//
        String uri = "http://api.ipstack.com/" + ipAddress +
                "?access_key=apiKey&fields=main";
        RestTemplate restTemplate = new RestTemplate();


        return restTemplate.getForObject(uri, String.class);
    }

    @GetMapping("lat")
    @ResponseBody
    private String getLat() throws UnknownHostException {
        String ipAddress = "81.227.88.189";//
        String uri = "http://api.ipstack.com/" + ipAddress +
                "?access_key=apiKey&fields=latitude";
        RestTemplate restTemplate = new RestTemplate();


        return restTemplate.getForObject(uri, String.class);
    }

    @GetMapping("lon")
    @ResponseBody
    private String getLon() throws UnknownHostException {
        String ipAddress = "81.227.88.189";//
        String uri = "http://api.ipstack.com/" + ipAddress +
                "?access_key=apiKey&fields=longitude";
        RestTemplate restTemplate = new RestTemplate();


        return restTemplate.getForObject(uri, String.class);
    }


    //&fields=longitude



}

