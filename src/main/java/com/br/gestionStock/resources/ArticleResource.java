package com.br.gestionStock.resources;

import com.br.gestionStock.doa.ArticleDoa;
import com.br.gestionStock.models.Article;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleResource {


    ArticleDoa articleDoa = new ArticleDoa();

    @GET
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
