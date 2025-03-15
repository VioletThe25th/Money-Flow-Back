package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Transaction")
public class ModelTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "owner", insertable = true, updatable = false, nullable = false)
    private ModelUser owner;

    @NotBlank
    @Column(name = "libelle", insertable = true, updatable = false, nullable = false)
    private String libelle;

    @NotNull
    @Column(name = "montant", insertable = true, updatable = false, nullable = false)
    private BigDecimal montant;

    @Valid
    @ManyToOne
    @JoinColumn(name = "compte_depart", insertable = true, updatable = false, nullable = false)
    private ModelCompte compteDepart;

    @Valid
    @ManyToOne
    @JoinColumn(name = "compte_destination", insertable = true, updatable = false, nullable = false)
    private ModelCompte compteDestination;

    @Valid
    @ManyToOne
    @JoinColumn(name = "classification_id", insertable = true, updatable = true, nullable = true)
    private ModelClassification classification;

    @Past
    @Column(name = "date", insertable = true, updatable = false, nullable = false)
    private Timestamp date;

    public ModelTransaction(){}

    public ModelTransaction(ModelUser user, String libelle, BigDecimal montant, ModelCompte compteDepart, ModelCompte compteDestination, ModelClassification classification, Timestamp date){
        this.owner = user;
        this.libelle = libelle;
        this.montant = montant;
        this.compteDepart = compteDepart;
        this.compteDestination = compteDestination;
        this.classification = classification;
        this.date = date;
    }

    public Long getId() {
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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

    public ModelClassification getClassification(){
        return classification;
    }

    public void setClassification(ModelClassification classification){
        this.classification = classification;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}