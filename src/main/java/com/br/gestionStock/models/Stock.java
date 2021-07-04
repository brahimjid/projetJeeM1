package com.br.gestionStock.models;

import javax.persistence.*;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Article article;
    private int quantite;

    public Stock() {

    }

    public Stock(Long id, Long article_id, int quantite) {
        this.id = id;
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", article=" + article +
                ", quantite=" + quantite +
                '}';
    }
}
