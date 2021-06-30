package com.br.gestionStock.resources;


import com.br.gestionStock.doa.OperationDoa;
import com.br.gestionStock.models.Operation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("operations")
public class OperationResource {
      OperationDoa operationDoa = new OperationDoa();
    @GET
    public List<Operation> fetchAll(){
        return operationDoa.getAll();
    }
     @Path("/entree")
     @GET
    public List<Operation> fetchAllEntree(){
        return operationDoa.getAllEntree();
    }

    @Path("/sortie")
     @GET
    public List<Operation> fetchAllSortie(){
        return operationDoa.getAllSortie();
    }

    @Path("/article/{idArticle}")
     @GET
    public List<Operation> articleOperation(@PathParam("idArticle") Long id){
        return operationDoa.ArticleOperations(id);
    }
}
