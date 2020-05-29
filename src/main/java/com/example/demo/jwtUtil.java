package com.example.demo;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.security.Key;


@Service
public class jwtUtil {
  final private SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
  Key key = Keys.secretKeyFor(algorithm);
  final private int EXPIRE_DURATION = 1000 * 3600 * 10; // 10 hours

  public String createUser(Student student){

    String jwt = Jwts.builder().setSubject(student.getName()).signWith(key).compact();
    return jwt;
  }

  public boolean validateUser(String jwt){
    String assumeUser = "Jacky";
    try {
      return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody().getSubject().equals(assumeUser);
    }
    catch (JwtException e){
      return false;
    }
  }

}
