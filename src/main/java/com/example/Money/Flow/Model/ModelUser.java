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

    @Column(name = "role", insertable = true, updatable = true, nullable = false)
    private BigInteger role;

    public User(){
        super();
    }

    public User(String nom, String prenom, String email, String password){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        email_confirme = false;
        role = BigInteger.valueOf(2); //2 ici est le role user de base
    }
}