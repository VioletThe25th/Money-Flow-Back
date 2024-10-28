package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.File;
import java.io.Serializable;

@Entity
@Table(name = "Categorie")
public class ModelCategorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=2)
    @Column(name = "libelle", insertable = true, updatable = true, nullable = false)
    private String libelle;

    @Column(name = "couleur", insertable = true, updatable = true, nullable = true)
    private String couleur;

    @Column(name = "icone", insertable = true, updatable = true, nullable = true)
    private String icone;

    public ModelCategorie(){}

    public ModelCategorie(String libelle, String couleur, String icone){
        this.libelle = libelle;
        this.couleur = couleur;
        this.icone = icone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}