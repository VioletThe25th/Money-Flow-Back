package com.example.Money.Flow.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Role")
public class ModelRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "role", insertable = true, updatable = true, nullable = false)
    private String role;

    public ModelRole(){}

    public ModelRole(String name){
        this.role = name;
    }
}
