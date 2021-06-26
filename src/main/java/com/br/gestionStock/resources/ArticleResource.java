package com.br.gestionStock.resources;

import com.br.gestionStock.doa.ArticleDoa;
import com.br.gestionStock.models.Article;

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
//    public Response index() {
//        return Response
//                .status(200)
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Headers",
//                        "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Methods",
//                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                .entity("test")
//                .build();
//    }
    public List<Article> getAll() {
        return articleDoa.getAll();
    }


    @GET
    @Path("/{idArticle}")
    public Article getById(@PathParam("idArticle") Long id) {

        return articleDoa.getById(id);
    }

    @POST
    public String add(Article article) {
        ArticleResource.this.articleDoa.add(article);
        return "Ok";
    }



    @PUT
    @Path("/{idArticle}")
    public String update(Article article, @PathParam("idArticle") Long id) {
        article.setId(id);
        ArticleResource.this.articleDoa.update(article);
        return "Ok";
    }


    @DELETE
    @Path("/{idArticle}")
    public String delete(@PathParam("idArticle") Long id) {


        ArticleResource.this.articleDoa.remove(ArticleResource.this.articleDoa.getById(id));

        return "removed";
    }

}
