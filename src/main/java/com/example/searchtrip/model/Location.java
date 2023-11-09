package com.example.searchtrip.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {

    @Id
    @GeneratedValue
    public String id;
    public String extId;
    public String name;
    public String lat;
    public String lon;
    public String time;
    public String date;
    public String stops;

}
