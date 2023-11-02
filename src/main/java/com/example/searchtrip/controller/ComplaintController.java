package com.example.searchtrip.controller;

import com.example.searchtrip.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/complaint")
public class ComplaintController {

    @Autowired
    EmailService emailService;

    @GetMapping("/complaint/{subject}/{body}")
    public void sendEmail(@RequestParam("subject") String subject, @RequestParam("body") String body) {
        emailService.sendEmail(subject, body);
    }
}
