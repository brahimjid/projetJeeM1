package com.br.gestionStock.resources;

import com.br.gestionStock.doa.AuthDoa;
import com.br.gestionStock.doa.UserDoa;
import com.br.gestionStock.models.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.jws.soap.SOAPBinding;
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
    UserDoa userdoa = new UserDoa();

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
            System.out.println(" catch error ");
            e.printStackTrace();
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public User register(User user) {
        return  userdoa.add(user);
    }
    @GET
    @Path("/logout/{id}")
      public boolean logout(@PathParam("id") long id){
       authDoa.removeToken(id);
        return true;
    }


    @GET
    @Path("/{token}")
      public User getT(@PathParam("token") String token){

        return authDoa.userByToken(token);
    }


}
