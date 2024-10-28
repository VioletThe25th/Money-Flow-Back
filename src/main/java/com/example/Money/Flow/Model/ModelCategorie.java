package com.example.Money.Flow.Model;

import jakarta.persistence.*;

import java.io.File;
import java.io.Serializable;

@Entity
@Table(name = "Categorie")
public class ModelCategorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle", insertable = true, updatable = true, nullable = false)
    private String libelle;

    @Column(name = "couleur", insertable = true, updatable = true, nullable = true)
    private String couleur;

    @Column(name = "icone", insertable = true, updatable = true, nullable = true)
    private File icone;

    public ModelCategorie(){}

    public ModelCategorie(String libelle, String couleur, File icone){
        this.libelle = libelle;
        this.couleur = couleur;
        this.icone = icone;
    }
}
