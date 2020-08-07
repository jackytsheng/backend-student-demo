package com.example.demo.util;

import com.example.demo.entity.Student;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;


@Service
public class JwtUtil {
  final private SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
  //This can't be hard coded for the future.
  String SECRETE_KEY = "JackySdLKDFJLKCNSDJFKSDLCXMXNCMVNMXCNVDJNWSDFSI";
  final private int EXPIRE_DURATION = 1000 * 3600 * 10; // 10 hours

  public String createUser(Student user){
    String jwt = Jwts.builder().setSubject(user.getName()).claim("role",user.getRole()).signWith(algorithm,SECRETE_KEY).compact();
    return jwt;
  }

  public boolean isAdmin(String jwt){
    try {
      System.out.println(Jwts.parserBuilder().setSigningKey(SECRETE_KEY).build().parseClaimsJws(jwt).getBody());
      return Jwts.parserBuilder().setSigningKey(SECRETE_KEY).build().parseClaimsJws(jwt).getBody().get("role").equals("admin");
    }
    catch (JwtException e){
      throw new JwtException("something goes wrong here");
    }
  }

}
