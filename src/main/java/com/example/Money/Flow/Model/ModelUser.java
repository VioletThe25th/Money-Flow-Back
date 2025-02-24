package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "User")
public class ModelUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private BigInteger id;

    @NotBlank
    @Size(min=2, max=25)
    @Column(name = "nom", insertable = true, updatable = true, nullable = false)
    private String nom;

    @NotBlank
    @Size(min=2, max=25)
    @Column(name = "prenom", insertable = true, updatable = true, nullable = false)
    private String prenom;

    @Email
    @NotBlank
    @Column(name = "email", insertable = true, unique = true, updatable = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "password", insertable = true, updatable = true, nullable = false)
    private String password;

    @Column(name = "email_confirme", insertable = true, updatable = true, nullable = true)
    private Boolean emailconfirmed;

    @Valid
    @OneToOne
    @JoinColumn(name = "role_id", insertable = true, updatable = true, nullable = false)
    private ModelRole role;

    public ModelUser(){}

    public ModelUser(String nom, String prenom, String email, String password, ModelRole role){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.emailconfirmed = false;
        this.role = role;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEmail_confirme() {
        return emailconfirmed;
    }

    public void setEmail_confirme(Boolean email_confirme) {
        this.emailconfirmed = email_confirme;
    }

    public ModelRole getRole() {
        return role;
    }

    public void setRole(ModelRole role) {
        this.role = role;
    }
}