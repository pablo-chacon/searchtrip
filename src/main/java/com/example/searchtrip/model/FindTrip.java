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

    public String originName;
    public String destName;
    public String stops;
    public String showPassingPoints;


}
