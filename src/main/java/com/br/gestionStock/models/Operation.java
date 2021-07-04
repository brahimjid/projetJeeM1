package com.br.gestionStock.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = javax.persistence.CascadeType.ALL,mappedBy="operation")
    @JsonManagedReference
    private List<OperationItem> operationItems;
    private double montant;
    private int type;
    private Timestamp created_at;


    public Operation() {
    }

    public Operation(Long id, List<OperationItem> operationItems, double montant, int type) {
        this.id = id;
        this.montant = montant;
        this.type = type;
        this.operationItems = operationItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OperationItem> getOperationItems() {
        return operationItems;
    }

    public void setOperationItems(List<OperationItem> operationItems) {
        this.operationItems = operationItems;
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
        return "Operation{" +
                "id=" + id +
                ", operationItems=" + operationItems +
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
