package com.br.gestionStock.doa;

import com.br.gestionStock.models.Article;
import com.br.gestionStock.models.Operation;
import com.br.gestionStock.models.OperationItem;
import com.br.gestionStock.models.Stock;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;

public class OperationDoa {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StockUnit");
    EntityManager em = emf.createEntityManager();
     final int TYPE_ENTREE =1;
     final int TYPE_SORTIE =2;
   StockDoa stockDoa = new StockDoa();
     public Operation addOperation(Operation operation){
            em.getTransaction().begin();
            em.persist(operation);
         em.getTransaction().commit();
         for (OperationItem item :operation.getOperationItems()){
             OperationItem op =  em.find(OperationItem.class, item.getId());
             op.setOperation_id(operation.getId());
             em.getTransaction().begin();
             em.persist(op);
             em.getTransaction().commit();

             if (op.getOperation().getType()==1){
                 Stock stock = new Stock();
                 stock.setQuantite(op.getQuantite());
                 stock.setArticle(op.getArticle());
                 stockDoa.add(stock);
             }
             else {
                 Stock stock = stockDoa.inStock(op.getArticle().getId());
                 stockDoa.removeQuantite(stock.getId(),op.getQuantite());
             }
               // add to  stock


         }



         return operation;

     }

     public Operation getOp(Long id){
         return em.find(Operation.class,id);
     }
    public List<Operation> getAll(){

        String sql = "select o from Operation o ";

        TypedQuery<Operation> qr = em.createQuery(sql, Operation.class);

        return qr.getResultList();

    }

    public List<Operation> getAllEntree(){

        String sql = "select o from Operation o where o.type="+TYPE_ENTREE;

        TypedQuery<Operation> qr = em.createQuery(sql, Operation.class);

        return qr.getResultList();

    }

    public List<Operation> getAllSortie(){

        String sql = "select o from Operation o  where o.type="+TYPE_SORTIE;

        TypedQuery<Operation> qr = em.createQuery(sql, Operation.class);

        return qr.getResultList();

    }

    public List<Operation> ArticleOperations(Long id){


        String sql = "select op from  Operation op join OperationItem oi on op.id = oi.operation_id and oi.article.id = "+id;

        TypedQuery<Operation> qr = em.createQuery(sql, Operation.class);
        return qr.getResultList();

    }

}
