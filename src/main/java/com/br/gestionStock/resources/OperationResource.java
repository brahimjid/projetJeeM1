package com.br.gestionStock.resources;
import com.br.gestionStock.doa.OperationDoa;
import com.br.gestionStock.models.Operation;
import com.br.gestionStock.models.OperationItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("operations")

public class OperationResource {
      OperationDoa operationDoa = new OperationDoa();

      @POST
      public Operation AddOperation(Operation operation){
          return  operationDoa.addOperation(operation);
      }

     @GET
     @Path("/{operationId}")
         public Operation getById(@PathParam("operationId") Long id){
          return operationDoa.getOp(id);
         }

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

    @DELETE
    @Path("/{idOperation}")
    public boolean delete (@PathParam("idOperation") Long id){
        operationDoa.remove(id);
        return true;
    }

}
