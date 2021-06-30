package com.br.gestionStock.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Operation {
    @Id
    private Long id;
    @ManyToOne()
   // @ManyToOne
    //@JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;
    private int quantite;
     private int type;

    public Operation() {
    }

    public Operation(Long id, Article article, int quantite, int type) {
        this.id = id;
        this.article = article;
        this.quantite = quantite;
        this.type = type;
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

    public String getType() {
        if (type==1)
        return "Entree";
        else
            return "Sortie";
    }

    public void setType(int type) {

        this.type = type;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", article=" + article +
                ", quantite=" + quantite +
                ", type='" + type + '\'' +
                '}';
    }
}
