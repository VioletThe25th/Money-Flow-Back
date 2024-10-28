package com.example.Money.Flow.Model;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.io.Serializable;

@Entity
@Table(name = "widget")
public class ModelWidget implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle", insertable = true, updatable = true, nullable = false)
    private String libelle;

    public ModelWidget(){}

    public ModelWidget(String libelle){
        this.libelle = libelle;
    }
}
