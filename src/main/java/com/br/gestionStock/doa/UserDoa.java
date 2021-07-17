package com.br.gestionStock.doa;

import com.br.gestionStock.models.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDoa {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StockUnit");
    EntityManager em = emf.createEntityManager();

    public User add(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    public User update(User user) {
        System.out.println("here ");
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        return user;
    }

    public boolean remove(User user) {

        em.getTransaction().begin();

        em.remove(user);

        em.getTransaction().commit();
           return true;
    }

    public User getById(Long id) {

     return em.find(User.class,id);

    }

    public List<User> getAll() {

        String sql = "select u from User u order by u.id desc ";

        TypedQuery<User> qr = em.createQuery(sql, User.class);

        return qr.getResultList();

    }
}
