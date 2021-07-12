package com.br.gestionStock.doa;

import com.br.gestionStock.models.Article;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ArticleDoa {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StockUnit");
    EntityManager em = emf.createEntityManager();


    public List<Article> getAll() {
        String sql = "select e from Article e ";
        TypedQuery<Article> qr = em.createQuery(sql, Article.class);
        return qr.getResultList();

    }

    public Article add(Article article) {
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();

        return article;

    }

    public Article update(Article article) {

        em.getTransaction().begin();

        em.merge(article);

        em.getTransaction().commit();
  return article;
    }

    public void remove(Long id) {
      Article article = em.find(Article.class,id);
        System.out.println(article.toString());
        em.getTransaction().begin();

        em.remove(article);

        em.getTransaction().commit();

    }

    public Article getById(Long id) {


           //return  em.find(Article.class,id);
        String sql = "select a from Article a where a.id =:id ";

        TypedQuery<Article> qr = em.createQuery(sql, Article.class);

        qr.setParameter("id", id);

        List<Article> articles = qr.getResultList();

        Article article = null;

        if (articles.size() > 0) {
            article = articles.get(0);
        }

        return article;

    }


}
