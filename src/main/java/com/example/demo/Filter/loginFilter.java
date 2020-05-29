package com.example.demo.Filter;

import lombok.Data;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Data
@Order(2)
@Component
public class loginFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException{
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    System.out.println( httpRequest.getHeader("Authentication"));
    chain.doFilter(request,response);
  }

}

