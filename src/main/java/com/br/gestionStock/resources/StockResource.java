package com.br.gestionStock.resources;

import com.br.gestionStock.doa.StockDoa;
import com.br.gestionStock.doa.StockDoa;
import com.br.gestionStock.models.Stock;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("stocks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StockResource {


    StockDoa stockDoa = new StockDoa();

    @GET
    public List<Stock> getAll() {
        return stockDoa.getAll();
    }

    @Path("/available")
    @GET
    public List<Stock> getAvailable() {
        return stockDoa.getAvailable();
    }


    @GET
    @Path("/{idStock}")
    public Stock getById(@PathParam("idStock") Long id) {

        return stockDoa.getById(id);
    }

    @POST
    public String add(Stock stock) {
        StockResource.this.stockDoa.add(stock);
        return "inserted";
    }

    @POST
    @Path("/{idStock}/{quantite}")
    @NotNull()
    public String removeFromQuantite(@Min(value = 1, message = "quantite must be greater then 0 ")
                                         @PathParam("idStock") Long id, @PathParam("quantite") int quantite) {
        StockResource.this.stockDoa.removeQuantite(id,quantite);
        return "stock update - ";
    }



    @PUT
    @Path("/{idStock}")
    public String update(Stock stock, @PathParam("idStock") Long id) {
        stock.setId(id);
        StockResource.this.stockDoa.update(stock);
        return "updated";
    }


    @DELETE
    @Path("/{idStock}")
    public String delete(@PathParam("idStock") Long id) {


        StockResource.this.stockDoa.remove(StockResource.this.stockDoa.getById(id));

        return "removed";
    }

}
