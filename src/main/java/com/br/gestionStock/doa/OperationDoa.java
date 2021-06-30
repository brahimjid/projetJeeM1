package com.br.gestionStock.doa;

import com.br.gestionStock.models.Operation;
import com.br.gestionStock.models.Stock;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class OperationDoa {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StockUnit");
    EntityManager em = emf.createEntityManager();
     final int TYPE_ENTREE =1;
     final int TYPE_SORTIE =2;
    public List<Operation> getAll(){

        String sql = "select o from Operation o join Article a";

        TypedQuery<Operation> qr = em.createQuery(sql, Operation.class);

        return qr.getResultList();

    }
    public List<Operation> getAllEntree(){

        String sql = "select o from Operation o join Article a where o.type="+TYPE_ENTREE;

        TypedQuery<Operation> qr = em.createQuery(sql, Operation.class);

        return qr.getResultList();

    }

    public List<Operation> getAllSortie(){

        String sql = "select o from Operation o join Article a where o.type="+TYPE_SORTIE;

        TypedQuery<Operation> qr = em.createQuery(sql, Operation.class);

        return qr.getResultList();

    }

    public List<Operation> ArticleOperations(Long id){

        String sql = "select o from Operation o  where o.article.id="+id;

        TypedQuery<Operation> qr = em.createQuery(sql, Operation.class);

        return qr.getResultList();

    }
}
