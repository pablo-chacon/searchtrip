package com.example.searchtrip.controller;

import com.example.searchtrip.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;



@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    private String hello() {
        return "Hello World";
    }

    @RequestMapping("/user")
    @ResponseBody
    private String getUser() {
        String uri = "https://jsonplaceholder.typicode.com/users/1";
        RestTemplate restTemplate = new RestTemplate();

        User user = restTemplate.getForObject(uri, User.class);
        System.out.println("User: " + user);

        return "User detail page.";
    }

}

