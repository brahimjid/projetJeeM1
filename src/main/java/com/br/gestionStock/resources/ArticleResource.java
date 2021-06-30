package com.br.gestionStock.resources;

import com.br.gestionStock.doa.ArticleDoa;
import com.br.gestionStock.models.Article;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleResource {


    ArticleDoa articleDoa = new ArticleDoa();

    @GET
    public List<Article> fetchAll() {
        return articleDoa.getAll();
    }


    @GET
    @Path("/{idArticle}")
    public Article getById(@PathParam("idArticle") Long id) {

        return articleDoa.getById(id);
    }

    @POST
    public String add(Article article) {
       articleDoa.add(article);
        return "Ok";
    }



    @PUT
    @Path("/{idArticle}")
    public String update(Article article, @PathParam("idArticle") Long id) {
        article.setId(id);
       articleDoa.update(article);
        return "Ok";
    }


    @DELETE
    @Path("/{idArticle}")
    public String delete(@PathParam("idArticle") Long id) {


       articleDoa.remove(articleDoa.getById(id));

        return "removed";
    }

}
