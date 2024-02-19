package com.example.searchtrip.model;

import lombok.Data;

import java.net.InetAddress;


@Data
public class User {

  private String username;
  private String password;
  private String email;
  private InetAddress ipAddress;

}