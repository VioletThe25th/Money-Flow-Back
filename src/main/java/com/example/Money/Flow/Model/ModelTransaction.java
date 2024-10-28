package com.example.Money.Flow.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Transaction")
public class ModelTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "owner", insertable = true, updatable = false, nullable = false)
    private ModelUser owner;

    @Column(name = "libelle", insertable = true, updatable = false, nullable = false)
    private String libelle;

    @Column(name = "montant", insertable = true, updatable = false, nullable = false)
    private float montant;

    @OneToOne
    @Column(name = "compte_depart", insertable = true, updatable = false, nullable = false)
    private ModelCompte compte_depart;

    @OneToOne
    @Column(name = "compte_destination", insertable = true, updatable = false, nullable = false)
    private ModelCompte compte_destination;

    @Column(name = "date", insertable = true, updatable = false, nullable = false)
    private Timestamp date;

    public ModelTransaction(){}

    public ModelTransaction(ModelUser user, String libelle, float montant, ModelCompte compte_depart, ModelCompte compte_destination, Timestamp date){
        this.owner = user;
        this.libelle = libelle;
        this.montant = montant;
        this.compte_depart = compte_depart;
        this.compte_destination = compte_destination;
        this.date = date;
    }
}
