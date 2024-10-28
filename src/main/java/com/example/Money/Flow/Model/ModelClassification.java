package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.io.Serializable;

@Entity
@Table(name = "Classification")
public class ModelClassification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne //Plusieurs transactions peuvent appartenir à une seule classification
    @JoinColumn(name = "transaction_id", insertable = true, updatable = true, nullable = true)
    private ModelTransaction transaction;

    @Valid
    @ManyToOne
    @JoinColumn(name = "categorie_id", insertable = true, updatable = true, nullable = true)
    private ModelCategorie categorie;

    public ModelClassification(){}

    public ModelClassification(ModelTransaction transaction, ModelCategorie categorie){
        this.transaction = transaction;
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(ModelTransaction transaction) {
        this.transaction = transaction;
    }

    public ModelCategorie getCategorie() {
        return categorie;
    }

    public void setCategorie(ModelCategorie categorie) {
        this.categorie = categorie;
    }
}