package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Entity
@Table(name = "Transaction")
public class ModelCompte implements Serializable {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "libelle", insertable = true, updatable = true, nullable = false)
    private String libelle;

    @Column(name = "solde", updatable = true, nullable = true)
    private float solde;

    @ManyToOne
    @JoinColumn(name = "owner", insertable = true, updatable = false, nullable = false)
    private ModelUser owner;

    @Column(name = "alerte_min", insertable = true, updatable = true, nullable = true)
    private float alerte_min;

    @Column(name = "alerte_max", insertable = true, updatable = true, nullable = true)
    private float alerte_max;

    @Column(name = "type", insertable = true, updatable = true, nullable = false)
    private int type;

    public ModelCompte(){}

    public ModelCompte(String libelle, float solde, ModelUser user, float alerte_min, float alerte_max, int type){
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
}