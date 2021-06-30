package com.br.gestionStock.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Entity
public class Article {
      @Id
      @GeneratedValue(strategy= GenerationType.IDENTITY)
     private Long id;
     private String ref;
     private String libelle;
     private double prix;
     private Date date_exp;

    @OneToMany(mappedBy = "article")
    private List<Operation> operations;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(Date date_exp) {
        this.date_exp = date_exp;
    }

    public Article() {
    }

    public Article(Long id, String ref, String libelle, double prix, Date date_exp) {
        this.id = id;
        this.ref = ref;
        this.libelle = libelle;
        this.prix = prix;
        this.date_exp = date_exp;
    }




    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", libelle='" + libelle + '\'' +
                ", prix=" + prix +
                ", date_exp=" + date_exp +
                '}';
    }
}
