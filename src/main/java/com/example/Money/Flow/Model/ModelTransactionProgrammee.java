package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "TransactionProgrammee")
public class ModelTransactionProgrammee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "owner", insertable = true, updatable = false, nullable = false)
    private ModelUser owner;

    @Valid
    @ManyToOne
    @JoinColumn(name = "compteDepart", insertable = true, updatable = true, nullable = false)
    private ModelCompte compteDepart;

    @Valid
    @ManyToOne
    @JoinColumn(name = "compteDestination", insertable = true, updatable = true, nullable = false)
    private ModelCompte compteDestination;

    @Positive
    @Column(name = "montant", insertable = true, updatable = true, nullable = false)
    private BigDecimal montant;

    @Column(name = "dateCreation", insertable = true, updatable = false, nullable = false)
    private Timestamp dateCreation;

    @Positive
    @Column(name = "frequence", insertable = true, updatable = true, nullable = true)
    private float frequence;

    @Future
    @Column(name = "dateTransaction", insertable = true, updatable = true, nullable = true)
    private Timestamp dateTransaction;

    public ModelTransactionProgrammee(ModelCompte compte_depart, ModelCompte compte_destination, BigDecimal montant, Timestamp date_creation, float frequence){
        this.compteDepart = compte_depart;
        this.compteDestination = compte_destination;
        this.montant = montant;
        this.dateCreation = Timestamp.valueOf(LocalDateTime.now());
        this.frequence = frequence;
    }

    public ModelTransactionProgrammee(ModelCompte compte_depart, ModelCompte compte_destination, BigDecimal montant, Timestamp date_creation, Timestamp date_transaction){
        this.compteDepart = compte_depart;
        this.compteDestination = compte_destination;
        this.montant = montant;
        this.dateCreation = date_creation;
        this.dateTransaction = date_transaction;
    }

    public ModelTransactionProgrammee() {}

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelUser getOwner() {
        return owner;
    }

    public void setOwner(ModelUser owner) {
        this.owner = owner;
    }

    public ModelCompte getCompteDepart() {
        return compteDepart;
    }

    public void setCompteDepart(ModelCompte compte_depart) {
        this.compteDepart = compte_depart;
    }

    public ModelCompte getCompteDestination() {
        return compteDestination;
    }

    public void setCompteDestination(ModelCompte compte_destination) {
        this.compteDestination = compte_destination;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Timestamp getDate_creation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp date_creation) {
        this.dateCreation = date_creation;
    }

    public float getFrequence() {
        return frequence;
    }

    public void setFrequence(float frequence) {
        this.frequence = frequence;
    }

    public Timestamp getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Timestamp date_transaction) {
        this.dateTransaction = date_transaction;
    }
}