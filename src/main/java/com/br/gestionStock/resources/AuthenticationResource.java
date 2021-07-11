package com.br.gestionStock.resources;

import com.br.gestionStock.doa.AuthDoa;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

@Path("/login")
public class AuthenticationResource {
    AuthDoa authDoa = new AuthDoa();
    @GET
    public Document login() throws IOException {
        String path = new File("./frontend/login.html").getCanonicalPath();
        Document htmlFile = null;
        try {

            htmlFile = Jsoup.parse(new File(path), "ISO-8859-1");
            return htmlFile;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();


        }
        return htmlFile;
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("login") String login, @FormParam("password") String password) {

        try {

            // Authenticate the user
            authDoa.authenticate(login, password);

            // Issue a token for the user
            String token = authDoa.generateToken(login);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


}
