package com.example.searchtrip.controller;

import com.example.searchtrip.model.UserDetails;
import com.example.searchtrip.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/*")
public class UserController {

  @PostMapping("login")
  public ResponseEntity<String> signIn(@RequestBody UserDetails userDetails) {
    String token = JwtUtil.sign(userDetails.getUsername());

    return ResponseEntity.ok(token);
  }

  @PostMapping("verify")
  public ResponseEntity<String> verifyToken(@RequestBody String token) {

    try {
      JwtUtil.verify(token);
      return ResponseEntity.ok("Token verified.");
    } catch(SignatureException ex) {
      return ResponseEntity.status(401).body("Invalid signature.");
    } catch(ExpiredJwtException ex) {
      return ResponseEntity.status(400).body("Token expired.");
    } catch(MalformedJwtException ex) {
      return ResponseEntity.status(400).body("Malformed token.");
    }
  }
}

