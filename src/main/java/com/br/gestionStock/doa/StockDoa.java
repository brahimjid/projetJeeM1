package com.br.gestionStock.doa;

import com.br.gestionStock.models.Article;
import com.br.gestionStock.models.Stock;

import javax.persistence.*;
import javax.ws.rs.POST;
import java.util.List;

public class StockDoa {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StockUnit");
    EntityManager em = emf.createEntityManager();

    public void add(Stock stock) {

            Stock articleStock = inStock(stock.getArticle().getId());
           if (articleStock!=null){
               Stock updatedStock = em.find(Stock.class,articleStock.getId());
               em.getTransaction().begin();
               updatedStock.setQuantite(updatedStock.getQuantite() + stock.getQuantite());
               em.getTransaction().commit();
               em.close();
           }

           else {
               em.getTransaction().begin();
               em.persist(stock);
               System.out.println("no article");
               em.getTransaction().commit();
               em.close();
           }


    }
    public void removeQuantite(Long id,int quantite){
        Stock updatedStock = em.find(Stock.class,id);
        em.getTransaction().begin();
        if (updatedStock.getQuantite()<1 && updatedStock.getQuantite()<=quantite){
            System.out.println("Quantité insuffisante" );
        }
        else {
            updatedStock.setQuantite(updatedStock.getQuantite() - quantite);
            em.getTransaction().commit();
        }

    }




    public void update(Stock stock) {

        em.getTransaction().begin();


        em.merge(stock);

        em.getTransaction().commit();
//        em.close();

    }

    public void addEntry(Long id,int quantite) {

           Stock stock = em.find(Stock.class,id);
           stock.setQuantite(stock.getQuantite()+quantite);
        em.getTransaction().begin();

        em.persist(stock);

        em.getTransaction().commit();

//        em.close();

    }

    public void remove(Stock stock) {

        em.getTransaction().begin();

        em.remove(stock);

        em.getTransaction().commit();
//        em.close();
    }

    public Stock getById(Long id) {

        String sql = "select a from Stock a where a.id =:id ";

        TypedQuery<Stock> qr = em.createQuery(sql, Stock.class);

        qr.setParameter("id", id);

        List<Stock> stocks = qr.getResultList();

        Stock stock = null;

        if (stocks.size() > 0) {
            stock = stocks.get(0);
        }

        return stock;

    }

    public Stock inStock(long article_id){
        String sql = "select a from Stock a where a.article.id =:id ";
        TypedQuery<Stock> qr = em.createQuery(sql, Stock.class);
        qr.setParameter("id", article_id);
        List<Stock> stocks = qr.getResultList();

        Stock stock = null;

        if (stocks.size() > 0) {
            stock = stocks.get(0);
        }

        return stock;
    }

    public List<Stock> getAll() {

        String sql = "select e from Stock e join Article a";

        TypedQuery<Stock> qr = em.createQuery(sql, Stock.class);

        return qr.getResultList();

    }
}
