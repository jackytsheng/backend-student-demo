package com.example.demo.Filter;

import com.example.demo.Util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(1)
public class AuthorizationFilter implements Filter {

  @Autowired
  jwtUtil jwt;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException{
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    System.out.println(httpRequest.getRequestURI());

    if (httpRequest.getRequestURI().equals("/adminAccess")){
      System.out.println("now in adminAccess");
      String Authorization = httpRequest.getHeader("Authorization");
      System.out.println(Authorization);
      System.out.println(jwt.isAdmin(Authorization));
      if(jwt.isAdmin(Authorization)){
          chain.doFilter(request,response);
        }else{
          HttpServletResponse httpServletResponse = (HttpServletResponse) response;
          httpServletResponse.sendError(403,"Unauthorised access.");
        }
    }else{
    chain.doFilter(request,response);
    }
  }


}
