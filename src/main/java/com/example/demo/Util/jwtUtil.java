package com.example.demo.Util;

import com.example.demo.Entity.Student;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;


@Service
public class jwtUtil {
  final private SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
  Key key = Keys.secretKeyFor(algorithm);
  final private int EXPIRE_DURATION = 1000 * 3600 * 10; // 10 hours

  public String createUser(Student user){

    String jwt = Jwts.builder().setSubject(user.getName()).claim("role",user.getRole()).signWith(key).compact();
    return jwt;
  }

  public boolean isAdmin(String jwt){
    try {
      return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody().get("role").equals("admin");
    }
    catch (JwtException e){
      new RuntimeException("Something going wrong");
      return false;
    }
  }

}
