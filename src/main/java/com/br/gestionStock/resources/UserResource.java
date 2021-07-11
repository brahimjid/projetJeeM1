package com.br.gestionStock.resources;

import com.br.gestionStock.doa.UserDoa;
import com.br.gestionStock.doa.UserDoa;
import com.br.gestionStock.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {


    UserDoa userDoa = new UserDoa();

    @GET
    public List<User> fetchAll() {
        return userDoa.getAll();
    }


    @GET
    @Path("/{idUser}")
    public User getById(@PathParam("idUser") Long id) {

        return userDoa.getById(id);
    }

    @POST
    public User add(User user) {
      return userDoa.add(user);
    }



    @PUT
    @Path("/{idUser}")
    public User update(User user, @PathParam("idUser") Long id) {
        user.setId(id);
       return userDoa.update(user);

    }


    @DELETE
    @Path("/{idUser}")
    public boolean delete(@PathParam("idUser") Long id) {
      return userDoa.remove(userDoa.getById(id));
    }

}
