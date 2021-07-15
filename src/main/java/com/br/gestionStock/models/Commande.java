package com.br.gestionStock.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="commande")
    @JsonManagedReference
    private List<CommandeItem> commande;
    private double montant;
    private int type;
    private Timestamp created_at;


    public Commande() {
    }

    public Commande(Long id, List<CommandeItem> commande, double montant, int type) {
        this.id = id;
        this.montant = montant;
        this.type = type;
        this.commande = commande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CommandeItem> getCommandeItems() {
        return commande;
    }

    public void setCommandeItems(List<CommandeItem> commande) {
        this.commande = commande;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", commande=" + commande +
                ", montant=" + montant +
                ", type=" + type +
                '}';
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
