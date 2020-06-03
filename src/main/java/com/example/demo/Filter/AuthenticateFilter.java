package com.example.demo.Filter;

import com.example.demo.Util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
@Order(1)
public class AuthenticateFilter implements Filter {

  @Autowired
  jwtUtil jwt;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException{
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    System.out.println(httpRequest.getRequestURI());

    if (httpRequest.getRequestURI().toString() == "/adminAccess"){
      System.out.println("now in adminAccess");
      String Authorization = httpRequest.getHeader("Authorization") != ""?httpRequest.getHeader("Authorization"):"";
      if(Authorization == ""){
        response.getWriter().print("Please Log in first");
      } else {
        if(jwt.isAdmin(Authorization)){
          chain.doFilter(request,response);
        }else{
          response.getWriter().print("You are not authorised to access this page");
        }
      }
    }
    chain.doFilter(request,response);
  }


}
