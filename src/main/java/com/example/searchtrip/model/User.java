package com.example.searchtrip.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.InetAddress;


@NoArgsConstructor
@Getter
@Setter
public class User {

    private Integer id;
    private InetAddress inetAddress;
    private String username;

}
