package com.br.gestionStock;

import com.br.gestionStock.doa.AuthDoa;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
//@PreMatching
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {
    AuthDoa authDoa = new AuthDoa();
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    /**
     * Method for ContainerRequestFilter.
     */
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
   String path = request.getUriInfo().getPath();
        System.out.println(path);
        if (path.equals("auth/login") || path.equals("auth/register")){
            return;
        }

        System.out.println("header = " +request.getHeaderString(HttpHeaders.AUTHORIZATION));
        String authorizationHeader = request.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!isTokenBasedAuthentication(authorizationHeader) ){

            request.abortWith(Response.ok().status(403).build());
        }
        else {
            System.out.println("headers not null");
            return;
//            String token = authorizationHeader
//                    .substring(AUTHENTICATION_SCHEME.length()).trim();
//            System.out.println("token=====");
//            System.out.println(token);
//            try {
//                // Validate the token
//                validateToken(token);
//             //  return;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                abortWithUnauthorized(request);
//            }
        }




        if (isPreflightRequest(request)) {
            request.abortWith(Response.ok().build());
            return;
        }
    }

    /**
     * A preflight request is an OPTIONS request
     * with an Origin header.
     */
    private static boolean isPreflightRequest(ContainerRequestContext request) {
        return request.getHeaderString("Origin") != null
                && request.getMethod().equalsIgnoreCase("OPTIONS");
    }

    /**
     * Method for ContainerResponseFilter.
     */
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response)
            throws IOException {
//        response.getHeaders().add(
//                "Access-Control-Allow-Origin", "*");
//        response.getHeaders().add(
//                "Access-Control-Allow-Credentials", "true");
//        response.getHeaders().add(
//                "Access-Control-Allow-Headers",
//                "origin, content-type, accept, authorization");
//        response.getHeaders().add(
//                "Access-Control-Allow-Methods",
//                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        }


    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        return authorizationHeader != null;
    }

    private void abortWithUnauthorized(ContainerRequestContext request) {
        request.abortWith(Response.ok().status(403).build());
    }

    private void validateToken(String token) throws Exception {

        if (authDoa.userByToken(token) == null){
            throw new Exception();
        }
    }



}

