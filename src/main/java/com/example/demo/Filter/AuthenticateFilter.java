package com.example.demo.Filter;

//import com.example.demo.jwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//@Component
//@Order(1)
//public class AuthenticateFilter implements Filter {
//
//  jwtUtil jwt = new jwtUtil();
//
//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response,
//                       FilterChain chain) throws IOException, ServletException{
//    HttpServletRequest httpRequest = (HttpServletRequest) request;
//    String Authorization = httpRequest.getHeader("Authorization") != ""?httpRequest.getHeader("Authorization"):"";
//    if (Authorization != ""){
//      if(!jwt.isAuthorized(Authorization)){
//        ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/unauthorised");
//        chain.doFilter(request,response);
//      }
//    }else{chain.doFilter(request,response);}
//  }
//
//
//}
