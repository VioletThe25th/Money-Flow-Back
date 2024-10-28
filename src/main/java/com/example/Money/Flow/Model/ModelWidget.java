package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.annotation.processing.Generated;
import java.io.Serializable;

@Entity
@Table(name = "widget")
public class ModelWidget implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max=25)
    @Column(name = "libelle", insertable = true, updatable = true, nullable = false)
    private String libelle;

    public ModelWidget(){}

    public ModelWidget(String libelle){
        this.libelle = libelle;
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
}