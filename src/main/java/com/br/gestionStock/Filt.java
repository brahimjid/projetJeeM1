package com.br.gestionStock;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.util.Enumeration;

public class Filt implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filt init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String authorizationHeader = request.getHeaderString(HttpHeaders.AUTHORIZATION);
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        if (headerNames != null) {

            while (headerNames.hasMoreElements()) {
                if (httpRequest.getHeader("Authorization")!=null){
                    System.out.println("not null");
                    chain.doFilter(request, response);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
