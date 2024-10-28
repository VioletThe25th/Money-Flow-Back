package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.io.Serializable;

@Entity
@Table(name = "Tableau")
public class ModelTableau implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", insertable = true, updatable = false, nullable = false)
    private ModelUser owner;

    public ModelTableau(){}

    public ModelTableau(ModelUser user){
        this.owner = user;
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
}