package com.br.gestionStock.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "commande_item")
public class CommandeItem
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

     @ManyToOne
     @JoinColumn(name="commande_id",insertable=false, updatable=false, nullable=true)
     @JsonBackReference
     private Commande commande;

     @OneToOne
     Article article;

    private int quantite;

    private Long commande_id;
    private double prix_unitaire;
    private double prix_total;
    public CommandeItem() {
    }

    public Long getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(Long commande_id) {
        this.commande_id = commande_id;
    }

    public CommandeItem(Long id, Commande commande, Article article, int quantite) {
        this.id = id;
        this.article = article;
        this.quantite = quantite;
        this.commande = commande;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
