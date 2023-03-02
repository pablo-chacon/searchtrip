package com.example.searchtrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;


@Controller
@RequestMapping("/geo")
public class GeoController {



    @GetMapping("/data")
    @ResponseBody
    private String getGeoData() throws UnknownHostException {
        String ipAddress = "81.227.88.189";//
        String uri = "http://api.ipstack.com/" + ipAddress +
                "?access_key=d7cda786b503c77ab5ac4dfd377cb0a9&fields=main";
        RestTemplate restTemplate = new RestTemplate();


        return restTemplate.getForObject(uri, String.class);
    }

}

