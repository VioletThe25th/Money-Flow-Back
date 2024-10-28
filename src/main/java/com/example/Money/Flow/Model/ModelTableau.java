package com.example.Money.Flow.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Tableau")
public class ModelTableau implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "owner", insertable = true, updatable = false, nullable = false)
    private ModelUser owner;

    public ModelTableau(){}

    public ModelTableau(ModelUser user){
        this.owner = user;
    }
}
