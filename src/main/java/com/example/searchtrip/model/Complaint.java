package com.example.searchtrip.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Complaint {

    @Id
    @GeneratedValue
    private String head;
    private String body;

}
