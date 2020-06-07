package com.example.demo.filter;

import com.example.demo.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;


@Slf4j
@Component
@Order(1)
public class AuthorizationFilter implements Filter {

  @Autowired
  JwtUtil jwt;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException{
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    httpServletResponse.setContentType("application/json");
    log.info(httpRequest.getRequestURI());

    if (httpRequest.getRequestURI().equals("/adminAccess")){
      String Authorization = httpRequest.getHeader("Authorization");
      if (Authorization == null){
        //may be format issue that this isn't showing
        httpServletResponse.sendError(SC_UNAUTHORIZED,"You need to log in first");
      }else{
      if(jwt.isAdmin(Authorization)){
          chain.doFilter(request,response);
        }else{
        httpServletResponse.getWriter().write("Unauthorised access.");
          httpServletResponse.sendError(SC_FORBIDDEN);
        }
    }}
    else{
    chain.doFilter(request,response);
    }
  }


}
