package com.example.Money.Flow.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Classification")
public class ModelClassification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "transaction_id", insertable = true, updatable = true, nullable = true)
    private ModelTransaction transaction;

    @ManyToOne
    @JoinColumn(name = "categorie_id", insertable = true, updatable = true, nullable = true)
    private ModelCategorie categorie;

    public ModelClassification(){}

    public ModelClassification(ModelTransaction transaction, ModelCategorie categorie){
        this.transaction = transaction;
        this.categorie = categorie;
    }
}
