//package com.example.demo.Filter;
//
//import lombok.Data;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Data
//@Order(2)
//@Component
//public class loginFilter implements Filter {
//
//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response,
//                       FilterChain chain) throws IOException, ServletException{
//    PrintWriter out = response.getWriter();
//    HttpServletRequest httpRequest = (HttpServletRequest) request;
//    if(httpRequest.getContextPath()!="authenticate"){
//      out.println("you need to login first");
//    };
//    chain.doFilter(request,response);
//  }
//
//}
//
