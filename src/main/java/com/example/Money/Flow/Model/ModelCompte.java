package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Transaction")
public class ModelCompte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @NotBlank
    @Size(min=3, max=25)
    @Column(name = "libelle", insertable = true, updatable = true, nullable = false)
    private String libelle;

    @Column(name = "solde", updatable = true, nullable = true)
    private BigDecimal solde;

    @Valid
    @ManyToOne
    @JoinColumn(name = "owner", insertable = true, updatable = false, nullable = false)
    private ModelUser owner;

    @PositiveOrZero
    @Column(name = "alerte_min", insertable = true, updatable = true, nullable = true)
    private BigDecimal alerte_min;

    @Positive
    @Column(name = "alerte_max", insertable = true, updatable = true, nullable = true)
    private BigDecimal alerte_max;

    @NotBlank
    @Positive
    @Column(name = "type", insertable = true, updatable = true, nullable = false)
    private int type;

    public ModelCompte(){}

    public ModelCompte(String libelle, BigDecimal solde, ModelUser user, BigDecimal alerte_min, BigDecimal alerte_max, int type){
        this.libelle = libelle;
        this.solde = solde;
        this.owner = user;
        this.alerte_min = alerte_min;
        this.alerte_max = alerte_max;
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public ModelUser getOwner() {
        return owner;
    }

    public void setOwner(ModelUser owner) {
        this.owner = owner;
    }

    public BigDecimal getAlerte_min() {
        return alerte_min;
    }

    public void setAlerte_min(BigDecimal alerte_min) {
        this.alerte_min = alerte_min;
    }

    public BigDecimal getAlerte_max() {
        return alerte_max;
    }

    public void setAlerte_max(BigDecimal alerte_max) {
        this.alerte_max = alerte_max;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}