package com.br.gestionStock.doa;

import com.br.gestionStock.PasswordHelper;
import com.br.gestionStock.models.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public  class AuthDoa {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StockUnit");
    EntityManager em = emf.createEntityManager();

    public  void  authenticate(String login,String password) throws Exception {

        String sql = "select u from User u where u.login = '"+login+"'";
        TypedQuery<User> qr = em.createQuery(sql, User.class);
        List<User> users = qr.getResultList();

        User user = users.get(0);
        if (users.size() == 0 || !(PasswordHelper.check(password,user.getPassword()))) {
            throw new Exception();
        }
    }
    public  User  generateToken(String login) throws Exception {
        String sql = "select u from User u where u.login = '"+login+"'";
        TypedQuery<User> qr = em.createQuery(sql, User.class);
        List<User> users = qr.getResultList();
         String token = UUID.randomUUID().toString() + users.get(0).getId();
        em.getTransaction().begin();

        User updateUser = em.find(User.class,users.get(0).getId());
          if (updateUser.getToken()==null){
              updateUser.setToken(token);
              em.persist(updateUser);
              em.getTransaction().commit();
          }

        return updateUser;
    }

    public  User  userByToken(String token){
        String sql = "select u from User u where u.token = '"+token+"'";
        TypedQuery<User> qr = em.createQuery(sql, User.class);
        List<User> users = qr.getResultList();
            User user =null;
        if (users.size() > 0) {
            user = users.get(0);
        }
        return user;
    }
     public void removeToken(long id){
          User user = em.find(User.class,id);
            em.getTransaction().begin();
            user.setToken(null);
            em.getTransaction().commit();

     }
}
