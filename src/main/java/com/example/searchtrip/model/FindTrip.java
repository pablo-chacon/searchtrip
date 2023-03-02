package com.example.searchtrip.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FindTrip {

    public String id;

    public String originId;
    public String destId;
    public String stops;
    public String showPassingPoints;


}
