package com.example.Money.Flow.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "TransactionProgrammee")
public class ModelTransactionProgrammee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "owner", insertable = true, updatable = false, nullable = false)
    private ModelUser owner;

    @Column(name = "compte_depart", insertable = true, updatable = true, nullable = false)
    private long compte_depart;

    @Column(name = "compte_destination", insertable = true, updatable = true, nullable = false)
    private long compte_destination;

    @Column(name = "montant", insertable = true, updatable = true, nullable = false)
    private float montant;

    @Column(name = "date_creation", insertable = true, updatable = true, nullable = false)
    private Timestamp date_creation;

    @Column(name = "frequence", insertable = true, updatable = true, nullable = true)
    private float frequence;

    @Column(name = "date_transaction", insertable = true, updatable = true, nullable = true)
    private Timestamp date_transaction;

    public ModelTransactionProgrammee(long compte_depart, long compte_destination, float montant, Timestamp date_creation, float frequence){
        this.compte_depart = compte_depart;
        this.compte_destination = compte_destination;
        this.montant = montant;
        this.date_creation = date_creation;
        this.frequence = frequence;
    }

    public ModelTransactionProgrammee(long compte_depart, long compte_destination, float montant, Timestamp date_creation, Timestamp date_transaction){
        this.compte_depart = compte_depart;
        this.compte_destination = compte_destination;
        this.montant = montant;
        this.date_creation = date_creation;
        this.date_transaction = date_transaction;
    }

    public ModelTransactionProgrammee() {}
}