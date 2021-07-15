package com.br.gestionStock.resources;

import com.br.gestionStock.doa.ArticleDoa;
import com.br.gestionStock.models.Article;
import org.jsoup.Jsoup;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
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




    @POST
    @Valid
    public Article add ( Article article){
        return articleDoa.add(article);

    }
        @GET
        @Path("/{idArticle}")
        public Article getById (@PathParam("idArticle") Long id){

            return articleDoa.getById(id);
        }




        @PUT
        @Path("/{idArticle}")
        public Article update (Article article, @PathParam("idArticle") Long id){
            article.setId(id);
           return articleDoa.update(article);

        }


        @DELETE
        @Path("/{idArticle}")
        public boolean delete (@PathParam("idArticle") Long id){
            articleDoa.remove(id);
            return true;
        }


    }
