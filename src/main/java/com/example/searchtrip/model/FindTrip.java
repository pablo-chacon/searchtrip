package com.example.searchtrip.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FindTrip {

    public String id;

    public String originLat, originLong;
    public String destLat, destLong;
    public String stops;
    public String showPassingPoints;


}
