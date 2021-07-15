package com.br.gestionStock.resources;

import com.br.gestionStock.doa.AuthDoa;
import com.br.gestionStock.models.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class AuthenticationResource {
    AuthDoa authDoa = new AuthDoa();

    @POST
    @Path("login")
    public Response authenticateUser(@FormParam("login") String login, @FormParam("password") String password) {

        try {

            // Authenticate the user
            authDoa.authenticate(login, password);

            // Issue a token for the user
            User userWithToken = authDoa.generateToken(login);

            // Return the token on the response
            return Response.status(Response.Status.OK).entity(userWithToken).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
    @GET
      public boolean logout(){

        return true;
    }
    @GET
    @Path("/{token}")
      public User getT(@PathParam("token") String token){

        return authDoa.userByToken(token);
    }


}
