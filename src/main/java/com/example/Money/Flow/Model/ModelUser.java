package com.example.Money.Flow.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "User")
public class ModelUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "nom", insertable = true, updatable = true, nullable = false)
    private String nom;

    @Column(name = "prenom", insertable = true, updatable = true, nullable = false)
    private String prenom;

    @Column(name = "email", insertable = true, unique = true, updatable = true, nullable = false)
    private String email;

    @Column(name = "password", insertable = true, updatable = true, nullable = false)
    private String password;

    @Column(name = "email_confirme", insertable = true, updatable = true, nullable = true)
    private Boolean email_confirme;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = true, updatable = true, nullable = false)
    private ModelRole role;

    public ModelUser(){}

    public ModelUser(String nom, String prenom, String email, String password, ModelRole role){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.email_confirme = false;
        this.role = role;
    }
}