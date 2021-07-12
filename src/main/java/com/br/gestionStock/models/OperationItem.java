package com.br.gestionStock.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "operation_item")
public class OperationItem
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

     @ManyToOne
     @JoinColumn(name="operation_id",insertable=false, updatable=false, nullable=true)
     @JsonBackReference
     private Operation operation;

     @OneToOne
     Article article;

    private int quantite;

    private Long operation_id;
    private double prix_unitaire;
    private double prix_total;
    public OperationItem() {
    }
    public Long getOperation_id() {
        return operation_id;
    }

    public void setOperation_id(Long operation_id) {
        this.operation_id = operation_id;
    }

    public OperationItem(Long id, Operation operation, Article article, int quantite) {
        this.id = id;
        this.article = article;
        this.quantite = quantite;
        this.operation = operation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
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
